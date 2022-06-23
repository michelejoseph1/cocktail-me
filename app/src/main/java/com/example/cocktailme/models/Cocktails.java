package com.example.cocktailme.models;


import android.util.Log;

import android.widget.ImageView;

import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;

import java.util.List;

@Parcel
public class Cocktails {
    String title;
    String cocktailPath;
    public static final String TAG = "Cocktails";

    public Cocktails() {
    }

    public Cocktails(JSONObject jsonObject) throws JSONException {
        title = jsonObject.getString("strDrink");
        cocktailPath = jsonObject.getString("strDrinkThumb");
        //recDesc = jsonObject.getString("strInstructions");


    }
    public static List<Cocktails> fromJsonArray(JSONArray recipeJsonArray) throws JSONException {
        List<Cocktails> ingredients = new ArrayList<>();
        for (int recipe = 0; recipe < recipeJsonArray.length(); recipe++) {
            ingredients.add(new Cocktails(recipeJsonArray.getJSONObject(recipe)));
import java.util.Arrays;
import java.util.List;

@Parcel
public class Cocktails {
    String title;
    String recDesc;
    String cocktailPath;
    public static final String TAG = "Cocktails";

    public Cocktails() {
    }

    public Cocktails(JSONObject jsonObject) throws JSONException {
        title = jsonObject.getString("strDrink");
        cocktailPath = jsonObject.getString("strDrinkThumb");
        //recDesc = jsonObject.getString("strInstructions");
        Log.d(TAG, "hi " + recDesc);


    }
    public static List<Cocktails> fromJsonArray(JSONArray recipeJsonArray) throws JSONException {
        List<Cocktails> ingredients = new ArrayList<>();
        for (int i = 0; i < recipeJsonArray.length(); i++) {


            ingredients.add(new Cocktails(recipeJsonArray.getJSONObject(i)));
        }
        return ingredients;
    }

    public String getCocktailPath() {
        return String.format(cocktailPath);

    }

    public String getRecipeTitle() {
        return title;
    }

}
