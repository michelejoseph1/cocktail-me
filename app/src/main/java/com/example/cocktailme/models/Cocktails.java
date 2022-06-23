package com.example.cocktailme.models;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
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

    public String getRecipeDesc() {
        return recDesc;
    }
}
