package com.example.cocktailme.db;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecipeModel implements Parcelable {

    public static final String TABLE_NAME = "recipes";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_RECIPE_NAME = "recipeName";
    public static final String COLUMN_GLASS = "glass";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_INSTRUCTIONS = "instructions";
    public static final String COLUMN_CATEGORY = "category";
    public static final String INGREDIENT1 = "ingredient1";
    public static final String INGREDIENT2 = "ingredient2";
    public static final String INGREDIENT3 = "ingredient3";
    public static final String INGREDIENT4 = "ingredient4";
    public static final String INGREDIENT5 = "ingredient5";
    public static final String INGREDIENT6 = "ingredient6";
    public static final String INGREDIENT7 = "ingredient7";
    public static final String INGREDIENT8 = "ingredient8";
    public static final String INGREDIENT9 = "ingredient9";
    public static final String INGREDIENT10 = "ingredient10";
    public static final String INGREDIENT11 = "ingredient11";
    public static final String INGREDIENT12 = "ingredient12";
    public static final String INGREDIENT13 = "ingredient13";
    public static final String INGREDIENT14 = "ingredient14";
    public static final String INGREDIENT15 = "ingredient15";
    public static final String MEASUREMENT1 = "measurement1";
    public static final String MEASUREMENT2 = "measurement2";
    public static final String MEASUREMENT3 = "measurement3";
    public static final String MEASUREMENT4 = "measurement4";
    public static final String MEASUREMENT5 = "measurement5";
    public static final String MEASUREMENT6 = "measurement6";
    public static final String MEASUREMENT7 = "measurement7";
    public static final String MEASUREMENT8 = "measurement8";
    public static final String MEASUREMENT9 = "measurement9";
    public static final String MEASUREMENT10 = "measurement10";
    public static final String MEASUREMENT11 = "measurement11";
    public static final String MEASUREMENT12 = "measurement12";
    public static final String MEASUREMENT13 = "measurement13";
    public static final String MEASUREMENT14 = "measurement14";
    public static final String MEASUREMENT15 = "measurement15";


    @SerializedName("idDrink")
    private int id;
    @SerializedName("strDrink")
    private String recipeName;
    @SerializedName("strGlass")
    private String glass;
    @SerializedName("strDrinkThumb")
    private String image;
    @SerializedName("strInstructions")
    private String instructions;
    @SerializedName("strCategory")
    private String category;
    @SerializedName("strIngredient1")
    private String ingredient1;
    @SerializedName("strIngredient2")
    private String ingredient2;
    @SerializedName("strIngredient3")
    private String ingredient3;
    @SerializedName("strIngredient4")
    private String ingredient4;
    @SerializedName("strIngredient5")
    private String ingredient5;
    @SerializedName("strIngredient6")
    private String ingredient6;
    @SerializedName("strIngredient7")
    private String ingredient7;
    @SerializedName("strIngredient8")
    private String ingredient8;
    @SerializedName("strIngredient9")
    private String ingredient9;
    @SerializedName("strIngredient10")
    private String ingredient10;
    @SerializedName("strIngredient11")
    private String ingredient11;
    @SerializedName("strIngredient12")
    private String ingredient12;
    @SerializedName("strIngredient13")
    private String ingredient13;
    @SerializedName("strIngredient14")
    private String ingredient14;
    @SerializedName("strIngredient15")
    private String ingredient15;
    @SerializedName("strMeasure1")
    private String measurement1;
    @SerializedName("strMeasure2")
    private String measurement2;
    @SerializedName("strMeasure3")
    private String measurement3;
    @SerializedName("strMeasure4")
    private String measurement4;
    @SerializedName("strMeasure5")
    private String measurement5;
    @SerializedName("strMeasure6")
    private String measurement6;
    @SerializedName("strMeasure7")
    private String measurement7;
    @SerializedName("strMeasure8")
    private String measurement8;
    @SerializedName("strMeasure9")
    private String measurement9;
    @SerializedName("strMeasure10")
    private String measurement10;
    @SerializedName("strMeasure11")
    private String measurement11;
    @SerializedName("strMeasure12")
    private String measurement12;
    @SerializedName("strMeasure13")
    private String measurement13;
    @SerializedName("strMeasure14")
    private String measurement14;
    @SerializedName("strMeasure15")
    private String measurement15;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + COLUMN_ID + " INTEGER PRIMARY KEY, "
                    + COLUMN_RECIPE_NAME + " TEXT, "
                    + COLUMN_GLASS + " TEXT, "
                    + COLUMN_IMAGE + " TEXT, "
                    + COLUMN_INSTRUCTIONS + " TEXT, "
                    + COLUMN_CATEGORY + " TEXT, "
                    + INGREDIENT1 + " TEXT, "
                    + INGREDIENT2 + " TEXT, "
                    + INGREDIENT3 + " TEXT, "
                    + INGREDIENT4 + " TEXT, "
                    + INGREDIENT5 + " TEXT, "
                    + INGREDIENT6 + " TEXT, "
                    + INGREDIENT7 + " TEXT, "
                    + INGREDIENT8 + " TEXT, "
                    + INGREDIENT9 + " TEXT, "
                    + INGREDIENT10 + " TEXT, "
                    + INGREDIENT11 + " TEXT, "
                    + INGREDIENT12 + " TEXT, "
                    + INGREDIENT13 + " TEXT, "
                    + INGREDIENT14 + " TEXT, "
                    + INGREDIENT15 + " TEXT, "
                    + MEASUREMENT1 + " TEXT, "
                    + MEASUREMENT2 + " TEXT, "
                    + MEASUREMENT3 + " TEXT, "
                    + MEASUREMENT4 + " TEXT, "
                    + MEASUREMENT5 + " TEXT, "
                    + MEASUREMENT6 + " TEXT, "
                    + MEASUREMENT7 + " TEXT, "
                    + MEASUREMENT8 + " TEXT, "
                    + MEASUREMENT9 + " TEXT, "
                    + MEASUREMENT10 + " TEXT, "
                    + MEASUREMENT11 + " TEXT, "
                    + MEASUREMENT12 + " TEXT, "
                    + MEASUREMENT13 + " TEXT, "
                    + MEASUREMENT14 + " TEXT, "
                    + MEASUREMENT15 + " TEXT)";


    public static final String DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public RecipeModel() {
    }
// We are counting to 15 because there is a maximum of 15 possible ingredients/measurements for each recipe
    public RecipeModel(Parcel in) {
        this.id = in.readInt();
        this.recipeName = in.readString();
        this.glass = in.readString();
        this.image = in.readString();
        this.instructions = in.readString();
        this.category = in.readString();
        this.ingredient1 = in.readString();
        this.ingredient2 = in.readString();
        this.ingredient3 = in.readString();
        this.ingredient4 = in.readString();
        this.ingredient5 = in.readString();
        this.ingredient6 = in.readString();
        this.ingredient7 = in.readString();
        this.ingredient8 = in.readString();
        this.ingredient9 = in.readString();
        this.ingredient10 = in.readString();
        this.ingredient11 = in.readString();
        this.ingredient12 = in.readString();
        this.ingredient13 = in.readString();
        this.ingredient14 = in.readString();
        this.ingredient15 = in.readString();
        this.measurement1 = in.readString();
        this.measurement2 = in.readString();
        this.measurement3 = in.readString();
        this.measurement4 = in.readString();
        this.measurement5 = in.readString();
        this.measurement6 = in.readString();
        this.measurement7 = in.readString();
        this.measurement8 = in.readString();
        this.measurement9 = in.readString();
        this.measurement10 = in.readString();
        this.measurement11 = in.readString();
        this.measurement12 = in.readString();
        this.measurement13 = in.readString();
        this.measurement14 = in.readString();
        this.measurement15 = in.readString();


    }

    public ArrayList<String> getAllIngredients() {
        ArrayList<String> allIngredients = new ArrayList<>();
        if (ingredient1 != null) {
            allIngredients.add(ingredient1);
        }
        if (ingredient2 != null) {
            allIngredients.add(ingredient2);
        }
        if (ingredient3 != null) {
            allIngredients.add(ingredient3);
        }
        if (ingredient4 != null) {
            allIngredients.add(ingredient4);
        }
        if (ingredient5 != null) {
            allIngredients.add(ingredient5);
        }
        if (ingredient6 != null) {
            allIngredients.add(ingredient6);
        }
        if (ingredient7 != null) {
            allIngredients.add(ingredient7);
        }
        if (ingredient8 != null) {
            allIngredients.add(ingredient8);
        }
        if (ingredient9 != null) {
            allIngredients.add(ingredient9);
        }
        if (ingredient10 != null) {
            allIngredients.add(ingredient10);
        }
        if (ingredient11 != null) {
            allIngredients.add(ingredient11);
        }
        if (ingredient12 != null) {
            allIngredients.add(ingredient12);
        }
        if (ingredient13 != null) {
            allIngredients.add(ingredient13);
        }
        if (ingredient14 != null) {
            allIngredients.add(ingredient14);
        }
        if (ingredient15 != null) {
            allIngredients.add(ingredient15);
        }
        return allIngredients;
    }

    public ArrayList<String> getAllMeasurements() {
        ArrayList<String> allMeasurements = new ArrayList<>();
        if (measurement1 != null) {
            allMeasurements.add(measurement1);
        }
        if (measurement2 != null) {
            allMeasurements.add(measurement2);
        }
        if (measurement3 != null) {
            allMeasurements.add(measurement3);
        }
        if (measurement4 != null) {
            allMeasurements.add(measurement4);
        }
        if (measurement5 != null) {
            allMeasurements.add(measurement5);
        }
        if (measurement6 != null) {
            allMeasurements.add(measurement6);
        }
        if (measurement7 != null) {
            allMeasurements.add(measurement7);
        }
        if (measurement8 != null) {
            allMeasurements.add(measurement8);
        }
        if (measurement9 != null) {
            allMeasurements.add(measurement9);
        }
        if (measurement10 != null) {
            allMeasurements.add(measurement10);
        }
        if (measurement11 != null) {
            allMeasurements.add(measurement11);
        }
        if (measurement12 != null) {
            allMeasurements.add(measurement12);
        }
        if (measurement13 != null) {
            allMeasurements.add(measurement13);
        }
        if (measurement14 != null) {
            allMeasurements.add(measurement14);
        }
        if (measurement15 != null) {
            allMeasurements.add(measurement15);
        }
        return allMeasurements;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(recipeName);
        dest.writeString(glass);
        dest.writeString(image);
        dest.writeString(instructions);
        dest.writeString(category);
        dest.writeString(ingredient1);
        dest.writeString(ingredient2);
        dest.writeString(ingredient3);
        dest.writeString(ingredient4);
        dest.writeString(ingredient5);
        dest.writeString(ingredient6);
        dest.writeString(ingredient7);
        dest.writeString(ingredient8);
        dest.writeString(ingredient9);
        dest.writeString(ingredient10);
        dest.writeString(ingredient11);
        dest.writeString(ingredient12);
        dest.writeString(ingredient13);
        dest.writeString(ingredient14);
        dest.writeString(ingredient15);
        dest.writeString(measurement1);
        dest.writeString(measurement2);
        dest.writeString(measurement3);
        dest.writeString(measurement4);
        dest.writeString(measurement5);
        dest.writeString(measurement6);
        dest.writeString(measurement7);
        dest.writeString(measurement8);
        dest.writeString(measurement9);
        dest.writeString(measurement10);
        dest.writeString(measurement11);
        dest.writeString(measurement12);
        dest.writeString(measurement13);
        dest.writeString(measurement14);
        dest.writeString(measurement15);
    }

    /**
     * This part is needed for Android to be able to create new objects, individually or as arrays
     */
    public static final Parcelable.Creator<RecipeModel> CREATOR = new Parcelable.Creator<RecipeModel>() {
        @Override
        public RecipeModel createFromParcel(Parcel source) {
            return new RecipeModel((source));
        }

        @Override
        public RecipeModel[] newArray(int size) {
            return new RecipeModel[size];
        }

    };


    @Override
    public boolean equals(Object object) {
        if (object instanceof RecipeModel) {
            RecipeModel toCompare = (RecipeModel) object;
            return (this.recipeName.equalsIgnoreCase((toCompare.getRecipeName())));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (this.getRecipeName().hashCode());
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String name) {
        this.recipeName = name;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIngredient1() {
        return ingredient1;
    }
    

    public void setIngredient1(String ingredient1) {
        this.ingredient1 = ingredient1;
    }

    public String getIngredient2() {
        return ingredient2;
    }

    public void setIngredient2(String ingredient2) {
        this.ingredient2 = ingredient2;
    }

    public String getIngredient3() {
        return ingredient3;
    }

    public void setIngredient3(String ingredient3) {
        this.ingredient3 = ingredient3;
    }

    public String getIngredient4() {
        return ingredient4;
    }

    public void setIngredient4(String ingredient4) {
        this.ingredient4 = ingredient4;
    }

    public String getIngredient5() {
        return ingredient5;
    }

    public void setIngredient5(String ingredient5) {
        this.ingredient5 = ingredient5;
    }

    public String getIngredient6() {
        return ingredient6;
    }

    public void setIngredient6(String ingredient6) {
        this.ingredient6 = ingredient6;
    }

    public String getIngredient7() {
        return ingredient7;
    }

    public void setIngredient7(String ingredient7) {
        this.ingredient7 = ingredient7;
    }

    public String getIngredient8() {
        return ingredient8;
    }

    public void setIngredient8(String ingredient8) {
        this.ingredient8 = ingredient8;
    }

    public String getIngredient9() {
        return ingredient9;
    }

    public void setIngredient9(String ingredient9) {
        this.ingredient9 = ingredient9;
    }

    public String getIngredient10() {
        return ingredient10;
    }

    public void setIngredient10(String ingredient10) {
        this.ingredient10 = ingredient10;
    }

    public String getIngredient11() {
        return ingredient11;
    }

    public void setIngredient11(String ingredient11) {
        this.ingredient11 = ingredient11;
    }

    public String getIngredient12() {
        return ingredient12;
    }

    public void setIngredient12(String ingredient12) {
        this.ingredient12 = ingredient12;
    }

    public String getIngredient13() {
        return ingredient13;
    }

    public void setIngredient13(String ingredient13) {
        this.ingredient13 = ingredient13;
    }

    public String getIngredient14() {
        return ingredient14;
    }

    public void setIngredient14(String ingredient14) {
        this.ingredient14 = ingredient14;
    }

    public String getIngredient15() {
        return ingredient15;
    }

    public void setIngredient15(String ingredient15) {
        this.ingredient15 = ingredient15;
    }

    public String getMeasurement1() {
        return measurement1;
    }

    public void setMeasurement1(String measurement1) {
        this.measurement1 = measurement1;
    }

    public String getMeasurement2() {
        return measurement2;
    }

    public void setMeasurement2(String measurement2) {
        this.measurement2 = measurement2;
    }

    public String getMeasurement3() {
        return measurement3;
    }

    public void setMeasurement3(String measurement3) {
        this.measurement3 = measurement3;
    }

    public String getMeasurement4() {
        return measurement4;
    }

    public void setMeasurement4(String measurement4) {
        this.measurement4 = measurement4;
    }

    public String getMeasurement5() {
        return measurement5;
    }

    public void setMeasurement5(String measurement5) {
        this.measurement5 = measurement5;
    }

    public String getMeasurement6() {
        return measurement6;
    }

    public void setMeasurement6(String measurement6) {
        this.measurement6 = measurement6;
    }

    public String getMeasurement7() {
        return measurement7;
    }

    public void setMeasurement7(String measurement7) {
        this.measurement7 = measurement7;
    }

    public String getMeasurement8() {
        return measurement8;
    }

    public void setMeasurement8(String measurement8) {
        this.measurement8 = measurement8;
    }

    public String getMeasurement9() {
        return measurement9;
    }

    public void setMeasurement9(String measurement9) {
        this.measurement9 = measurement9;
    }

    public String getMeasurement10() {
        return measurement10;
    }

    public void setMeasurement10(String measurement10) {
        this.measurement10 = measurement10;
    }


    public String getMeasurement11() {
        return measurement11;
    }

    public void setMeasurement11(String measurement11) {
        this.measurement11 = measurement11;
    }

    public String getMeasurement12() {
        return measurement12;
    }

    public void setMeasurement12(String measurement12) {
        this.measurement12 = measurement12;
    }

    public String getMeasurement13() {
        return measurement13;
    }

    public void setMeasurement13(String measurement13) {
        this.measurement13 = measurement13;
    }

    public String getMeasurement14() {
        return measurement14;
    }

    public void setMeasurement14(String measurement14) {
        this.measurement14 = measurement14;
    }

    public String getMeasurement15() {
        return measurement15;
    }

    public void setMeasurement15(String measurement15) {
        this.measurement15 = measurement15;
    }


    public static List<RecipeModel> fromJsonArray(JSONArray cocktailJsonArray) throws JSONException {
        List<RecipeModel> cocktailList= new ArrayList<>();
        for (int cocktail = 0; cocktail < cocktailJsonArray.length(); cocktail++) {
            cocktailList.add(new RecipeModel(cocktailJsonArray.getJSONObject(cocktail)));
        }
        return cocktailList;
    }
    public RecipeModel(JSONObject jsonObject) throws JSONException {
        setRecipeName(jsonObject.getString("strDrink"));
        setImage(jsonObject.getString("strDrinkThumb"));
        setId(jsonObject.getInt("idDrink"));
    }
}