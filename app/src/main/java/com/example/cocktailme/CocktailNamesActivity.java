package com.example.cocktailme;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestHeaders;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.cocktailme.adapters.CocktailAdapter;
import com.example.cocktailme.db.RecipeModel;
import com.example.cocktailme.fragments.HomeFragment;
import com.example.cocktailme.fragments.ProfileFragment;
import com.example.cocktailme.fragments.SettingsFragment;
import com.example.cocktailme.models.Cocktails;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;

public class CocktailNamesActivity extends AppCompatActivity {
    private final String TAG = "CocktailNamesActivity";
    public AsyncHttpClient client;
    String insertedIngredients;
    String cocktailTitle;
    int cocktailID;
    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;

    private HashMap<String, String> ingredientMesurementMap = new HashMap<>();
    private String name;
    private String instruction;
    private String image;
    private ArrayList<String> ingredients = new ArrayList<>();
    private ArrayList<String> measurements = new ArrayList<>();
    private RecipeModel recipeModel;
    public static final String INGREDIENT_LIST_URL = "https://the-cocktail-db.p.rapidapi.com/filter.php";
public List<RecipeModel> cocktails;
    public CocktailAdapter cocktailAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        Cocktails cocktail = new Cocktails();
        cocktailTitle = cocktail.getRecipeTitle();
        cocktailID = cocktail.getID();
        Log.d("checking to confirm ID", "Results: " + cocktailID);
        client = new AsyncHttpClient();
        insertedIngredients = getIntent().getStringExtra("search");
        cocktails = new ArrayList<>();
        cocktailAdapter = new CocktailAdapter(this, cocktails);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_settings:
                        fragment = new SettingsFragment();

                        break;
                    case R.id.action_profile:
                        fragment = new ProfileFragment();
                        break;

                    default:
                        HomeFragment tempFragment = new HomeFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("search", insertedIngredients);
                        tempFragment.setArguments(bundle);
                        fragment = tempFragment;
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.action_home);




        getRecipesMethod(insertedIngredients);

    }
//doesn't work with commas
    public void getRecipesMethod(String insertedIngredients) {
        RequestHeaders headers = new RequestHeaders();
        RequestParams params = new RequestParams();
        params.put("i", insertedIngredients);
        Log.d(TAG, "results: " + insertedIngredients);
        headers.put("X-RapidAPI-Key", "c13dadbdfdmshb5f916990392087p1e49ccjsnae8ca04335f1");
        headers.put("X-RapidAPI-Host", "the-cocktail-db.p.rapidapi.com");


        client.get(INGREDIENT_LIST_URL, headers, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess. The response is " + json.toString());
                JSONObject jsonObject = json.jsonObject;

                try {
                    JSONArray drinks = jsonObject.getJSONArray("drinks");
                    cocktails.addAll(RecipeModel.fromJsonArray(drinks));
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


        //goRecipeDetailsFromCache();

//    private void goRecipeDetailsFromCache() {
//        Intent ingredientsToRecipes = getIntent();
//        recipeModel = ingredientsToRecipes.getParcelableExtra("currRecipe");
//
//        DatabaseHelper databaseHelper = new DatabaseHelper(this);
//        TextView layoutTitle = findViewById(R.id.detailRecipeTitle);
//        TextView instructions = findViewById(R.id.detailRecipeInstruction);
//        ImageView detailImage = findViewById(R.id.detailsImage);
//        ListView ingredientsListView = findViewById(R.id.ingredientsListView);
//        Button deleteButton = findViewById(R.id.delete_button);
//        if (!databaseHelper.checkExist(recipeModel.getId())) {
//            deleteButton.setVisibility(View.GONE);
//        }
//        ingredients = recipeModel.getAllIngredients();
//        measurements = recipeModel.getAllMeasurements();
//        image = recipeModel.getImage();
//        instruction = recipeModel.getInstructions();
//        name = recipeModel.getRecipeName();
//
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ingredientsListView.getLayoutParams();
//        params.height = 200 * ingredients.size();
//        ingredientsListView.setLayoutParams(params);
//        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(ingredients, measurements);
//        ingredientsListView.setAdapter(ingredientsAdapter);
//        Log.d(TAG, "onCreate: " + instruction);
//        Log.d(TAG, "onCreate: " + ingredients);
//        Picasso.get().load(image).into(detailImage);
//        instructions.setText(instruction);
//        layoutTitle.setText(name);
//    }
}