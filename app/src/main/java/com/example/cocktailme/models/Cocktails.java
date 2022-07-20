package com.example.cocktailme.models;


import com.example.cocktailme.Rating;

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
    Rating rating;
    public List<Rating> ratingsList;

    public static final String TAG = "Cocktails";

    public Cocktails() {
    }

    public Cocktails(JSONObject jsonObject) throws JSONException {
        title = jsonObject.getString("strDrink");
        cocktailPath = jsonObject.getString("strDrinkThumb");
        cocktailID = jsonObject.getInt("idDrink");
    }
    public int getAverageRating() {
        List<Rating> ratings = new ArrayList<Rating>();
        int total = 0;
        for (int i = 0; i < ratings.size(); ++i) {
            total += Integer.parseInt(String.valueOf(ratings));
        }
        int avg = (int) total / ratings.size();
        return avg;
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




