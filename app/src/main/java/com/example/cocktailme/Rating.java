package com.example.cocktailme;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Rating")
public class Rating extends ParseObject {

    public static String KEY_NUMRATING = "numRating";
    public static String KEY_USER = "user";

    public String getRating() {
        return getString(KEY_NUMRATING);
    }
    public void setRating(String numRating) {
        put(KEY_NUMRATING, numRating);
    }
    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }
    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

}
