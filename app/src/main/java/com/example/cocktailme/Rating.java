package com.example.cocktailme;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Rating")
public class Rating extends ParseObject {

    public static String KEY_NUMSTARS = "numStars";
    public static String KEY_USER = "user";
    public static String COCKTAIL_ID = "cocktailID";


    public int getRating() {
        return getInt(KEY_NUMSTARS);
    }
    public void setRating(String numRating) {
        put(KEY_NUMSTARS, numRating);
    }
    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }
    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }
    public int getCocktailId(){
        return getInt(COCKTAIL_ID);
    }
    public void setCocktailId(int cocktailId) {
        put(COCKTAIL_ID, cocktailId);
    }


}
