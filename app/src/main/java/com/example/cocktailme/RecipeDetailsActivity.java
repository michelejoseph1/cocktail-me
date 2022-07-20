package com.example.cocktailme;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestHeaders;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.cocktailme.db.RecipeModel;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Headers;

public class RecipeDetailsActivity extends AppCompatActivity {
    public static final String TAG = "RecipeDetailsActivity";
    public static final String INGREDIENT_LIST_URL = "https://the-cocktail-db.p.rapidapi.com/lookup.php";
    RecipeModel recipeModel;
    TextView recipeTitle, recipeInstructions, measurementsText;
    public AsyncHttpClient client;
    int cocktailID;
    ImageView cocktailImage;
    RatingBar ratingBar;
    Double voteAverage;
    protected List<Rating> ratingsList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        recipeTitle = findViewById(R.id.detailRecipeTitle);
        recipeInstructions = findViewById(R.id.instructionTitle);
        measurementsText = findViewById(R.id.detailRecipeInstruction);
        cocktailImage = findViewById(R.id.ivCocktail);
        recipeModel = (RecipeModel) getIntent().getParcelableExtra(RecipeModel.class.getName());
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        recipeTitle.setText(recipeModel.getRecipeName());
        client = new AsyncHttpClient();
        cocktailID = recipeModel.getId();
        getInstructions(cocktailID);
        queryRatingsForCocktailID();
        queryUserForCocktailID();
}

    public void setRatingText(View v) {
        TextView t = (TextView) findViewById(R.id.avgRatingText);
        t.setText("The average rating for this cocktail is: ");
    }

    public void getInstructions(int cocktailID) {
        RequestHeaders headers = new RequestHeaders();
        RequestParams params = new RequestParams();
        params.put("i", cocktailID);
        headers.put("X-RapidAPI-Key", "c13dadbdfdmshb5f916990392087p1e49ccjsnae8ca04335f1");
        headers.put("X-RapidAPI-Host", "the-cocktail-db.p.rapidapi.com");

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
                            String measurements = getMeasurements(drinks);
                            recipeInstructions.setText(instructions);
                            measurementsText.setText(measurements);
                            //Log.d("RecipeDetails", "Results: " + measurements.toString());
                            Log.d("RecipeDetails", "Results: " + instructions.toString());
                            String imageURL;
                            imageURL = recipeModel.getImage();
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

    public String getMeasurements(JSONArray drinks) throws JSONException {
        String measurements = "";
        for (int i = 1; i < 16; i++) {
            String curr = "strIngredient" + i;
            String measure = "strMeasure" + i;
            if (drinks.getJSONObject(0).getString(curr) != null && drinks.getJSONObject(0).getString(measure) != null

            ) {
                measurements += drinks.getJSONObject(0).getString(measure) + " " + drinks.getJSONObject(0).getString(curr) + "\n ";
            } else {
                measurements = "Measurements not found";
            }
        }
        return measurements;

    }

    private void queryRatingsForCocktailID() {
        ParseQuery<Rating> query = ParseQuery.getQuery(Rating.class);
        query.include(Rating.KEY_USER);
        query.include(String.valueOf(Rating.COCKTAIL_ID));
        query.addDescendingOrder("createdAt");
        query.whereEqualTo(Rating.KEY_USER, "cocktailID");
        query.findInBackground(new FindCallback<Rating>() {
            @Override
            public void done(List<Rating> ratings, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (Rating rating : ratings) {
                    Log.i(TAG, "Post: " + rating.getRating() + ", username: " + rating.getUser().getUsername());
                }
                ratingsList.addAll(ratings);
            }
        });
    }
    private void queryUserForCocktailID() {
        ParseQuery<Rating> query = ParseQuery.getQuery(Rating.class);
        query.include(Rating.KEY_USER);
        query.include(String.valueOf(Rating.COCKTAIL_ID));
        query.addDescendingOrder("createdAt");
        query.whereEqualTo(Rating.COCKTAIL_ID, "user");
        query.findInBackground(new FindCallback<Rating>() {
            @Override
            public void done(List<Rating> ratings, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (Rating rating : ratings) {
                    Log.i(TAG, "Post: " + rating.getCocktailId() + ", username: " + rating.getUser().getUsername());
                }
                ratingsList.addAll(ratings);
            }
        });
    }
    }

