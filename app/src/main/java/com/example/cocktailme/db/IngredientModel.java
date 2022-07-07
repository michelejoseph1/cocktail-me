package com.example.cocktailme.db;

import com.google.gson.annotations.SerializedName;

public class IngredientModel {
    @SerializedName("idIngredient")
    private int id;
    @SerializedName("strIngredient")
    private String name;
    @SerializedName("strDescription")
    private String description;
    @SerializedName("strType")
    private String type;
    @SerializedName("strAlcohol")
    private String ifAlcohol;

    public IngredientModel(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIfAlcohol() {
        return ifAlcohol;
    }

    public void setIfAlcohol(String ifAlcohol) {
        this.ifAlcohol = ifAlcohol;
    }

}
