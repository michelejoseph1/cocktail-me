package com.example.cocktailme.models;


import android.util.Log;

import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Parcel
public class Ingredients {
    String ingredientName;
    String title;
    String recipeDesc;

    public Ingredients() {
    }

    public Ingredients(JSONObject jsonObject) throws JSONException {
        JSONArray drinks = jsonObject.getJSONArray("drinks");

        JSONObject strDrink = drinks.getJSONObject(0);
        title = strDrink.getString("strDrink");
        Log.d("key", "hi" + title);

    }
    public static List<Ingredients> fromJsonArray(JSONArray recipeJsonArray) throws JSONException {
        List<Ingredients> ingredients = new ArrayList<>();
        for (int i = 0; i < recipeJsonArray.length(); i++) {
            ingredients.add(new Ingredients(recipeJsonArray.getJSONObject(i)));
        }
        return ingredients;
    }

    public String getRecipeTitle() {
        return title;
    }

    public String getRecipeDesc() {
        return recipeDesc;
    }
}

