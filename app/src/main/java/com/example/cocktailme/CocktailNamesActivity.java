package com.example.cocktailme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestHeaders;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.cocktailme.adapters.CocktailAdapter;
import com.example.cocktailme.models.Cocktails;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Headers;

public class CocktailNamesActivity extends AppCompatActivity {
    public static final String INGREDIENT_LIST_URL = "https://the-cocktail-db.p.rapidapi.com/filter.php";
    public static final String TAG = "CocktailNamesActivity";
    public AsyncHttpClient client;
    String insertedIngredients;
    String cocktailTitle;
    int cocktailID;
    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        RecyclerView rvRecipes = findViewById(R.id.rvRecipes);
        Cocktails cocktail = new Cocktails();
        cocktailTitle = cocktail.getRecipeTitle();
        cocktailID = cocktail.getID();
        Log.d("checking to confirm ID", "Results: " + cocktailID);
        client = new AsyncHttpClient();
        insertedIngredients = getIntent().getStringExtra("search");
        getRecipesMethod(insertedIngredients);
       // goRecipeDetails();


        bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        //TODO: update fragment
                        // Toast.makeText(MainActivity.this, "Home!", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.action_compose:
                        //Toast.makeText(MainActivity.this, "Compose!", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.action_profile:

                    default:

                        //Toast.makeText(MainActivity.this, "Profile!", Toast.LENGTH_SHORT).show();
                        //fragment= new ProfileFragment();
                        break;
                }
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);
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
                    rvRecipes.setLayoutManager(new LinearLayoutManager(CocktailNamesActivity.this));


                    JSONArray drinks = jsonObject.getJSONArray("drinks");
                    ingredients.addAll(Cocktails.fromJsonArray(drinks));
                    cocktailAdapter.notifyDataSetChanged();
                    Log.d(TAG, "Results: " + drinks.toString());

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
<<<<<<< HEAD:app/src/main/java/com/example/cocktailme/CocktailNamesActivity.java
    private void goRecipeDetails() {
        Intent cocktailNamestoRecipes = new Intent(this, RecipeDetailsActivity.class);
        cocktailNamestoRecipes.putExtra("ID", cocktailID);
        startActivity(cocktailNamestoRecipes);
        finish();
    }
=======
>>>>>>> c68224e (adds cocktail names to RV and adds images with glide):app/src/main/java/com/example/cocktailme/RecipesActivity.java
}