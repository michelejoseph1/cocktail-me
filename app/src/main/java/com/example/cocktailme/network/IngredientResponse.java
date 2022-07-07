package com.example.cocktailme.network;

import com.example.cocktailme.db.IngredientModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientResponse {

    @SerializedName("ingredients")
    private List<IngredientModel> ingredientModels;

    public List<IngredientModel> getIngredient() {
        return ingredientModels;
    }
    public void setIngredient(List<IngredientModel> ingredientModel) {
        this.ingredientModels = ingredientModel;
    }
}