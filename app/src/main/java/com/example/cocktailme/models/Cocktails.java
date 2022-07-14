package com.example.cocktailme.models;


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
    int cocktailID;
    public static final String TAG = "Cocktails";

    public Cocktails() {
    }

    public Cocktails(JSONObject jsonObject) throws JSONException {
        title = jsonObject.getString("strDrink");
        cocktailPath = jsonObject.getString("strDrinkThumb");
        cocktailID = jsonObject.getInt("idDrink");
    }

    public static List<Cocktails> fromJsonArray(JSONArray cocktailJsonArray) throws JSONException {
        List<Cocktails> cocktailList = new ArrayList<>();
        for (int cocktail = 0; cocktail < cocktailJsonArray.length(); cocktail++) {
            cocktailList.add(new Cocktails(cocktailJsonArray.getJSONObject(cocktail)));
        }
        return cocktailList;
    }


    public String getCocktailPath() {
        return String.format(cocktailPath);

    }
    public String getRecipeTitle() {
        return title;
    }
    public int getID() {
        return cocktailID;
    }

}




