package com.example.cocktailme.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestHeaders;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.cocktailme.R;
import com.example.cocktailme.adapters.CocktailAdapter;
import com.example.cocktailme.models.Cocktails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Headers;


public class HomeFragment extends Fragment {
    public static final String INGREDIENT_LIST_URL = "https://the-cocktail-db.p.rapidapi.com/filter.php";
    public AsyncHttpClient client;
    String insertedIngredients;
    String cocktailTitle;
    int cocktailID;
    RecyclerView rvRecipes;
    ArrayList ingredients;
    LinearLayoutManager manager;
    public static final String TAG = "HomeFragment";


    public HomeFragment() {
    }


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Add the following lines to create RecyclerView
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvRecipes = view.findViewById(R.id.rvRecipes);
        ingredients = new ArrayList<>();
        manager = new LinearLayoutManager(getContext());
        rvRecipes.setLayoutManager(manager);
        rvRecipes.setHasFixedSize(true);
        CocktailAdapter cocktailAdapter = new CocktailAdapter(getContext(), ingredients);
        rvRecipes.setAdapter(cocktailAdapter);


        Cocktails cocktail = new Cocktails();
        cocktailTitle = cocktail.getRecipeTitle();
        cocktailID = cocktail.getID();
        Log.d("checking to confirm ID", "Results: " + cocktailID);
        client = new AsyncHttpClient();
        insertedIngredients = this.getArguments().getString("search");


        RequestHeaders headers = new RequestHeaders();
        RequestParams params = new RequestParams();
        params.put("i", insertedIngredients);
        headers.put("X-RapidAPI-Key", "c13dadbdfdmshb5f916990392087p1e49ccjsnae8ca04335f1");
        headers.put("X-RapidAPI-Host", "the-cocktail-db.p.rapidapi.com");

        rvRecipes.setLayoutManager(manager);
        rvRecipes.setHasFixedSize(true);
        rvRecipes.setAdapter(cocktailAdapter);


        client.get(INGREDIENT_LIST_URL, headers, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess. The response is " + json.toString());
                JSONObject jsonObject = json.jsonObject;

                try {
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
}
