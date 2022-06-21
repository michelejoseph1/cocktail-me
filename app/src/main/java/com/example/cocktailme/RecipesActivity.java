package com.example.cocktailme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestHeaders;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.cocktailme.adapters.CocktailAdapter;
import com.example.cocktailme.models.Ingredients;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class RecipesActivity extends AppCompatActivity {
    public static final String INGREDIENT_LIST_URL = "https://the-cocktail-db.p.rapidapi.com/filter.php";
    public static final String TAG = "RecipesActivity";
    public AsyncHttpClient client;
    String insertedIngredients;
    List<Ingredients> ingredients;
    List<String> drinkNames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        RecyclerView rvRecipes = findViewById(R.id.rvRecipes);
        ArrayList ingredients = new ArrayList<>();



        client = new AsyncHttpClient();
        insertedIngredients = getIntent().getStringExtra("search");
        getRecipesMethod(insertedIngredients);
    }

    public void getRecipesMethod(String insertedIngredients) {
        RequestHeaders headers = new RequestHeaders();
        RequestParams params = new RequestParams();
        params.put("i", insertedIngredients);
        headers.put("X-RapidAPI-Key", "c13dadbdfdmshb5f916990392087p1e49ccjsnae8ca04335f1");
        headers.put("X-RapidAPI-Host", "the-cocktail-db.p.rapidapi.com");

        RecyclerView rvRecipes = findViewById(R.id.rvRecipes);
        ArrayList ingredients = new ArrayList<>();

        //create adapter
        CocktailAdapter cocktailAdapter = new CocktailAdapter(this, ingredients);

        //set adapter on the recycler view
        rvRecipes.setAdapter(cocktailAdapter);

        //set the layout manager on the recycler vie
        rvRecipes.setLayoutManager(new LinearLayoutManager(this));


        client.get(INGREDIENT_LIST_URL, headers, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess. The response is " + json.toString());
                JSONObject jsonObject = json.jsonObject;
                try {
                    rvRecipes.setAdapter(cocktailAdapter);

                    //set the layout manager on the recycler vie
                    rvRecipes.setLayoutManager(new LinearLayoutManager(RecipesActivity.this));


                    JSONArray drinks = jsonObject.getJSONArray("drinks");
                    ingredients.addAll(Ingredients.fromJsonArray(drinks));
                    cocktailAdapter.notifyDataSetChanged();
                    Log.i(TAG, "Resu: " + drinks.toString());




//                    JSONObject strDrink = drinks.getJSONObject(0);
//                    String drink = strDrink.getString("strDrink");


                    //Log.d(TAG, "Results: " + drink.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
                @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "onFailure" + statusCode + response);
            }
        });
    }
}