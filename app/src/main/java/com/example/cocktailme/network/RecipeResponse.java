package com.example.cocktailme.network;

import com.example.cocktailme.db.RecipeModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeResponse {

    @SerializedName("drinks")
    private List<RecipeModel> recipeModels;

    public List<RecipeModel> getRecipeModels() {
        return recipeModels;
    }

    public void setRecipeModels(List<RecipeModel> recipeModels) {
        this.recipeModels = recipeModels;
    }
}
