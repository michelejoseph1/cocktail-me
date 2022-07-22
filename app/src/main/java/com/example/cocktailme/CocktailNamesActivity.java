package com.example.cocktailme;

import static com.example.cocktailme.models.Constants.HOME_LINK;
import static com.example.cocktailme.models.Constants.SEARCH_API_LINK;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Headers;

public class CocktailNamesActivity extends AppCompatActivity {
    private final String TAG = "CocktailNamesActivity";
    public AsyncHttpClient client;
    String insertedIngredients;
    String cocktailTitle;
    int cocktailID;
    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;
    public static final String INGREDIENT_LIST_URL = "https://the-cocktail-db.p.rapidapi.com/filter.php";
    public List<RecipeModel> cocktails;
    public ArrayList<Cocktails> cocktailsArray;
    public CocktailAdapter cocktailAdapter;
    FirebaseFirestore firestoreHolder;
    ArrayList<String> ratings;
    ArrayAdapter ratingsArrayAdapter;
<<<<<<< HEAD
    protected RatingsAdapter adapter;
    protected List<Rating> allRatings;

=======
>>>>>>> parent of 4696ad5 (adds ratingsadapter to population numAverageRatings and update recyclerview with highest ratings across all users)

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


        firestoreHolder = FirebaseFirestore.getInstance();
        Map<String, Object> drinks = new HashMap<>();
        drinks.put("gin", cocktail.getRecipeTitle());
        firestoreHolder.collection("drinks").add(drinks).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();
            }
        });

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
<<<<<<< HEAD
        //queryAverageRatings();
        getRecipes(insertedIngredients);
=======
        getRecipesMethod(insertedIngredients);
>>>>>>> parent of 4696ad5 (adds ratingsadapter to population numAverageRatings and update recyclerview with highest ratings across all users)
    }

    public void getRecipesMethod(String insertedIngredients) {
        RequestHeaders headers = new RequestHeaders();
        RequestParams params = new RequestParams();
        params.put("i", insertedIngredients);
        Log.d(TAG, "results: " + insertedIngredients);
        headers.put("X-RapidAPI-Key", SEARCH_API_LINK);
        headers.put("X-RapidAPI-Host", HOME_LINK);
        Log.d("search link", "Results: " + SEARCH_API_LINK);
        Log.d("home link", "Results: " + HOME_LINK);


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
                    cocktailsArray.addAll(Cocktails.fromJsonArray(drinks));
                    Log.d(TAG, "ArrayList results: " + drinks.toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "onFailure" + statusCode + response);
            }
        });
        sortRecipesMethod(cocktailsArray);
    }

    public void sortRecipesMethod(ArrayList<Cocktails> cocktailsArrayList) {
        ratingsArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ratings);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("numRating");
        query.addAscendingOrder("numRating");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> ratings, ParseException e) {
                if (e == null) {
                    if (ratings.size() > 0) {
                        for (ParseObject rating : ratings) {
                            ratingsArrayAdapter.add(String.valueOf(rating.getInt("numRating")));
                        }
                        ratingsArrayAdapter.notifyDataSetChanged();
                    }
                }

            }
        });
<<<<<<< HEAD

//    private void queryAverageRatings() {
//        ParseQuery<Rating> query = ParseQuery.getQuery(Rating.class);
//        query.include(Rating.KEY_USER);
//        query.addDescendingOrder("averageNumStars");
//        query.findInBackground(new FindCallback<Rating>() {
//            @Override
//            public void done(List<Rating> ratings, ParseException e) {
//                if (e != null) {
//                    Log.e(TAG, "Issue with getting ratings", e);
//                    return;
//                }
//                for (Rating rating : ratings) {
//                    Log.i(TAG, "Rating: " + rating.getRating() + ", username: " + rating.getUser().getUsername());
//                }
//                allRatings.addAll(ratings);
//                adapter.notifyDataSetChanged();
//            }
//        });
=======
>>>>>>> parent of 4696ad5 (adds ratingsadapter to population numAverageRatings and update recyclerview with highest ratings across all users)
    }
}
