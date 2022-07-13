package com.example.cocktailme;

import com.example.cocktailme.db.RecipeModel;
import com.example.cocktailme.models.Cocktails;

public class FeatureFlag {
    public boolean useCache;
    Cocktails cocktail;
    RecipeModel recipeModel;

    public void CacheOrApiCall(){

        useCache = false;
        if(useCache){
            CacheResponse(cocktail);
        }else{
            ApiResponse(recipeModel);
        }
    }

     String public void CacheResponse(Cocktails cocktail){
        cocktail.getRecipeTitle();
    }

    public String void ApiResponse(RecipeModel recipeModel){
        recipeModel.getRecipeName();
    }
}
