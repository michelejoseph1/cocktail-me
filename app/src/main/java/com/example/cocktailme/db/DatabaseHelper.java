package com.example.cocktailme.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "recipe_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(RecipeModel.CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL(RecipeModel.DELETE_TABLE);

        // Create tables again
        onCreate(db);
    }

    public boolean checkForTableExists(String table){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT name FROM sqlite_master WHERE type='table' AND name='"+table+"'";
        Cursor mCursor = db.rawQuery(query, null);
        if (mCursor.getCount() > 0) {
            return true;
        }
        mCursor.close();
        return false;
    }

    public void resetTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(RecipeModel.DELETE_TABLE);
        db.execSQL(RecipeModel.CREATE_TABLE);
        db.close();

    }

    public void insertRecipes(List<RecipeModel> recipeModels) {
        for (RecipeModel currentRecipeModel : recipeModels) {
            insertRecipe(currentRecipeModel);
        }
    }

    public void insertRecipe(RecipeModel recipeModel) {

        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        int id = recipeModel.getId();
        String recipeName = recipeModel.getRecipeName();
        String recipeGlass = recipeModel.getGlass();
        String recipeImage = recipeModel.getImage();
        String instructions = recipeModel.getInstructions();
        String category = recipeModel.getCategory();
        String ingredient1 = recipeModel.getIngredient1();
        String ingredient2 = recipeModel.getIngredient2();
        String ingredient3 = recipeModel.getIngredient3();
        String ingredient4 = recipeModel.getIngredient4();
        String ingredient5 = recipeModel.getIngredient5();
        String ingredient6 = recipeModel.getIngredient6();
        String ingredient7 = recipeModel.getIngredient7();
        String ingredient8 = recipeModel.getIngredient8();
        String ingredient9 = recipeModel.getIngredient9();
        String ingredient10 = recipeModel.getIngredient10();
        String ingredient11 = recipeModel.getIngredient11();
        String ingredient12 = recipeModel.getIngredient12();
        String ingredient13 = recipeModel.getIngredient13();
        String ingredient14 = recipeModel.getIngredient14();
        String ingredient15 = recipeModel.getIngredient15();
        String measurement1 = recipeModel.getMeasurement1();
        String measurement2 = recipeModel.getMeasurement2();
        String measurement3 = recipeModel.getMeasurement3();
        String measurement4 = recipeModel.getMeasurement4();
        String measurement5 = recipeModel.getMeasurement5();
        String measurement6 = recipeModel.getMeasurement6();
        String measurement7 = recipeModel.getMeasurement7();
        String measurement8 = recipeModel.getMeasurement8();
        String measurement9 = recipeModel.getMeasurement9();
        String measurement10 = recipeModel.getMeasurement10();
        String measurement11 = recipeModel.getMeasurement11();
        String measurement12 = recipeModel.getMeasurement12();
        String measurement13 = recipeModel.getMeasurement13();
        String measurement14 = recipeModel.getMeasurement14();
        String measurement15 = recipeModel.getMeasurement15();

        // input all values of this piece of data
        values.put(RecipeModel.COLUMN_ID, id);
        values.put(RecipeModel.COLUMN_RECIPE_NAME, recipeName);
        values.put(RecipeModel.COLUMN_GLASS, recipeGlass);
        values.put(RecipeModel.COLUMN_IMAGE, recipeImage);
        values.put(RecipeModel.COLUMN_INSTRUCTIONS, instructions);
        values.put(RecipeModel.COLUMN_CATEGORY, category);
        values.put(RecipeModel.INGREDIENT1, ingredient1);
        values.put(RecipeModel.INGREDIENT2, ingredient2);
        values.put(RecipeModel.INGREDIENT3, ingredient3);
        values.put(RecipeModel.INGREDIENT4, ingredient4);
        values.put(RecipeModel.INGREDIENT5, ingredient5);
        values.put(RecipeModel.INGREDIENT6, ingredient6);
        values.put(RecipeModel.INGREDIENT7, ingredient7);
        values.put(RecipeModel.INGREDIENT8, ingredient8);
        values.put(RecipeModel.INGREDIENT9, ingredient9);
        values.put(RecipeModel.INGREDIENT10, ingredient10);
        values.put(RecipeModel.INGREDIENT11, ingredient11);
        values.put(RecipeModel.INGREDIENT12, ingredient12);
        values.put(RecipeModel.INGREDIENT13, ingredient13);
        values.put(RecipeModel.INGREDIENT14, ingredient14);
        values.put(RecipeModel.INGREDIENT15, ingredient15);
        values.put(RecipeModel.MEASUREMENT1, measurement1);
        values.put(RecipeModel.MEASUREMENT2, measurement2);
        values.put(RecipeModel.MEASUREMENT3, measurement3);
        values.put(RecipeModel.MEASUREMENT4, measurement4);
        values.put(RecipeModel.MEASUREMENT5, measurement5);
        values.put(RecipeModel.MEASUREMENT6, measurement6);
        values.put(RecipeModel.MEASUREMENT7, measurement7);
        values.put(RecipeModel.MEASUREMENT8, measurement8);
        values.put(RecipeModel.MEASUREMENT9, measurement9);
        values.put(RecipeModel.MEASUREMENT10, measurement10);
        values.put(RecipeModel.MEASUREMENT11, measurement11);
        values.put(RecipeModel.MEASUREMENT12, measurement12);
        values.put(RecipeModel.MEASUREMENT13, measurement13);
        values.put(RecipeModel.MEASUREMENT14, measurement14);
        values.put(RecipeModel.MEASUREMENT15, measurement15);

        db.insert(RecipeModel.TABLE_NAME, null, values);
        db.close();
    }

    public boolean checkExist(int id) {
        Boolean exist = false;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(RecipeModel.TABLE_NAME,
                new String[]{RecipeModel.COLUMN_ID},
                RecipeModel.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null,null,null);

        int count = cursor.getCount();
        if (count == 0) {
            exist = false;
        } else {
            exist = true;
        }

        cursor.close();
        return exist;
    }

    public List<RecipeModel> getAllRecipes() {
        List<RecipeModel> recipeModels = new ArrayList<>();

        // Select All
        String selectQuery = "SELECT * FROM " + RecipeModel.TABLE_NAME + " ORDER BY " +
                RecipeModel.COLUMN_ID;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // loop through all rows and add to the list
        if (cursor.moveToFirst()) {
            do {
                RecipeModel current = new RecipeModel();
                current.setId(cursor.getInt(cursor.getColumnIndex(RecipeModel.COLUMN_ID)));
                current.setRecipeName(cursor.getString(cursor.getColumnIndex(RecipeModel.COLUMN_RECIPE_NAME)));
                current.setGlass(cursor.getString(cursor.getColumnIndex(RecipeModel.COLUMN_GLASS)));
                current.setImage(cursor.getString(cursor.getColumnIndex(RecipeModel.COLUMN_IMAGE)));
                current.setCategory((cursor.getString(cursor.getColumnIndex(RecipeModel.COLUMN_CATEGORY))));
                current.setInstructions(cursor.getString(cursor.getColumnIndex(RecipeModel.COLUMN_INSTRUCTIONS)));

                current.setIngredient1(cursor.getString(cursor.getColumnIndex(RecipeModel.INGREDIENT1)));
                current.setIngredient2(cursor.getString(cursor.getColumnIndex(RecipeModel.INGREDIENT2)));
                current.setIngredient3(cursor.getString(cursor.getColumnIndex(RecipeModel.INGREDIENT3)));
                current.setIngredient4(cursor.getString(cursor.getColumnIndex(RecipeModel.INGREDIENT4)));
                current.setIngredient5(cursor.getString(cursor.getColumnIndex(RecipeModel.INGREDIENT5)));
                current.setIngredient6(cursor.getString(cursor.getColumnIndex(RecipeModel.INGREDIENT6)));
                current.setIngredient7(cursor.getString(cursor.getColumnIndex(RecipeModel.INGREDIENT7)));
                current.setIngredient8(cursor.getString(cursor.getColumnIndex(RecipeModel.INGREDIENT8)));
                current.setIngredient9(cursor.getString(cursor.getColumnIndex(RecipeModel.INGREDIENT9)));
                current.setIngredient10(cursor.getString(cursor.getColumnIndex(RecipeModel.INGREDIENT10)));
                current.setIngredient11(cursor.getString(cursor.getColumnIndex(RecipeModel.INGREDIENT11)));
                current.setIngredient12(cursor.getString(cursor.getColumnIndex(RecipeModel.INGREDIENT12)));
                current.setIngredient13(cursor.getString(cursor.getColumnIndex(RecipeModel.INGREDIENT13)));
                current.setIngredient14(cursor.getString(cursor.getColumnIndex(RecipeModel.INGREDIENT14)));
                current.setIngredient15(cursor.getString(cursor.getColumnIndex(RecipeModel.INGREDIENT15)));

                current.setMeasurement1(cursor.getString(cursor.getColumnIndex(RecipeModel.MEASUREMENT1)));
                current.setMeasurement2(cursor.getString(cursor.getColumnIndex(RecipeModel.MEASUREMENT2)));
                current.setMeasurement3(cursor.getString(cursor.getColumnIndex(RecipeModel.MEASUREMENT3)));
                current.setMeasurement4(cursor.getString(cursor.getColumnIndex(RecipeModel.MEASUREMENT4)));
                current.setMeasurement5(cursor.getString(cursor.getColumnIndex(RecipeModel.MEASUREMENT5)));
                current.setMeasurement6(cursor.getString(cursor.getColumnIndex(RecipeModel.MEASUREMENT6)));
                current.setMeasurement7(cursor.getString(cursor.getColumnIndex(RecipeModel.MEASUREMENT7)));
                current.setMeasurement8(cursor.getString(cursor.getColumnIndex(RecipeModel.MEASUREMENT8)));
                current.setMeasurement9(cursor.getString(cursor.getColumnIndex(RecipeModel.MEASUREMENT9)));
                current.setMeasurement10(cursor.getString(cursor.getColumnIndex(RecipeModel.MEASUREMENT10)));
                current.setMeasurement11(cursor.getString(cursor.getColumnIndex(RecipeModel.MEASUREMENT11)));
                current.setMeasurement12(cursor.getString(cursor.getColumnIndex(RecipeModel.MEASUREMENT12)));
                current.setMeasurement13(cursor.getString(cursor.getColumnIndex(RecipeModel.MEASUREMENT13)));
                current.setMeasurement14(cursor.getString(cursor.getColumnIndex(RecipeModel.MEASUREMENT14)));
                current.setMeasurement15(cursor.getString(cursor.getColumnIndex(RecipeModel.MEASUREMENT15)));

                recipeModels.add(current);
            } while (cursor.moveToNext());
        }

        db.close();

        return recipeModels;
    }

    public int getRecipesCount() {
        String countQuery = "SELECT * FROM " + RecipeModel.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public void deleteRecipe(RecipeModel recipeModelData) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(RecipeModel.TABLE_NAME, RecipeModel.COLUMN_ID + " = ?",
                new String[]{String.valueOf(recipeModelData.getId())});
        db.close();
    }

    public void deleteTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(RecipeModel.DELETE_TABLE);
        db.close();
    }
}
