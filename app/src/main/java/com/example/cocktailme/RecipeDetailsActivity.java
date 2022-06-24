package com.example.cocktailme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestHeaders;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.cocktailme.adapters.CocktailAdapter;
import com.example.cocktailme.models.Cocktails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.ArrayList;

import okhttp3.Headers;

public class RecipeDetailsActivity extends AppCompatActivity {

    public static final String INGREDIENT_LIST_URL = "https://the-cocktail-db.p.rapidapi.com/lookup.php";
    Cocktails cocktail;
    TextView recipeTitle, recipeInstructions, measurementsText;
    public AsyncHttpClient client;
    int cocktailID;
    ImageView cocktailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        recipeTitle = findViewById(R.id.recipeTitle);
        recipeInstructions = findViewById(R.id.recipeInstructions);
        measurementsText = findViewById(R.id.measurementsText);
        cocktailImage = findViewById(R.id.cocktailImage);
        cocktail = (Cocktails) Parcels.unwrap(getIntent().getParcelableExtra(Cocktails.class.getSimpleName()));

        recipeTitle.setText(cocktail.getRecipeTitle());

        client = new AsyncHttpClient();

        cocktailID = cocktail.getID();
        //cocktailID = getIntent().getIntExtra("ID", 0);

        // Call getInstructions to
        getInstructions(cocktailID);

    }
    public void getInstructions(int cocktailID) {
        RequestHeaders headers = new RequestHeaders();
        RequestParams params = new RequestParams();
        params.put("i", cocktailID);
        headers.put("X-RapidAPI-Key", "c13dadbdfdmshb5f916990392087p1e49ccjsnae8ca04335f1");
        headers.put("X-RapidAPI-Host", "the-cocktail-db.p.rapidapi.com");

        ArrayList ingredients = new ArrayList<>();

        client.get(INGREDIENT_LIST_URL,
                headers,
                params,
                new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON json) {
                        JSONObject jsonObject = json.jsonObject;
                        try {
                            JSONArray drinks = jsonObject.getJSONArray("drinks");
                            String instructions = drinks.getJSONObject(0).getString("strInstructions");
                            String measurements =
                                    drinks.getJSONObject(0).getString("strMeasure1") +
                                    drinks.getJSONObject(0).getString("strIngredient1") +
                                    drinks.getJSONObject(0).getString("strMeasure2") +
                                    drinks.getJSONObject(0).getString("strIngredient2") +
                                    drinks.getJSONObject(0).getString("strMeasure3") +
                                    drinks.getJSONObject(0).getString("strIngredient3");

                                    recipeInstructions.setText(instructions);
                                    measurementsText.setText(measurements);
                           // ingredients.addAll(Cocktails.fromJsonArray(drinks));
                            Log.d("RecipeDetails", "Results: " + instructions.toString());
                            String imageURL;
                            imageURL = cocktail.getCocktailPath();
                            Glide.with(RecipeDetailsActivity.this).load(imageURL).override(500, 500)
                                    .fitCenter().into(cocktailImage);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                        Log.d("RecipeDetails", "onFailure" + statusCode + response);

                    }
                });
    }
}
