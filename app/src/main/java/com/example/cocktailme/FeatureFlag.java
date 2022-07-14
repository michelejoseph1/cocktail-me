package com.example.cocktailme;

import com.example.cocktailme.db.RecipeModel;
import com.example.cocktailme.models.Cocktails;

public class FeatureFlag {
    public boolean useCache;
    Cocktails cocktail;
    RecipeModel recipeModel;

    public void cacheOrApiCall() {

        useCache = false;
        if (useCache) {
            cacheResponse(cocktail);
        } else {
            apiResponse(recipeModel);
        }
    }

    public void cacheResponse(Cocktails cocktail) {
        cocktail.getRecipeTitle();
    }

    public void apiResponse(RecipeModel recipeModel) {
        recipeModel.getRecipeName();
    }

}
