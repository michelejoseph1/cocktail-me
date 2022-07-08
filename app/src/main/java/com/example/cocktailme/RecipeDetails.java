package com.example.cocktailme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cocktailme.db.DatabaseHelper;
import com.example.cocktailme.db.RecipeModel;
import com.example.cocktailme.fragments.IngredientsAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipeDetails extends AppCompatActivity {
    private static final String TAG = "RecipeDetails";
    private HashMap<String, String> ingredientMesurementMap = new HashMap<>();
    private String name;
    private String instruction;
    private String image;
    private String glass;
    private ArrayList<String> ingredients = new ArrayList<>();
    private ArrayList<String> measurements = new ArrayList<>();
    private RecipeModel recipeModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        recipeModel = intent.getParcelableExtra("MyRecipe");

        DatabaseHelper db = new DatabaseHelper(this);
        TextView layoutTitle = findViewById(R.id.detailRecipeTitle);
        TextView instructions = findViewById(R.id.detailRecipeInstruction);
        ImageView detailImage = findViewById(R.id.detailsImage);
        ListView ingredientsListView = findViewById(R.id.ingredientsListView);
        Button deleteButton = findViewById(R.id.delete_button);
        if (!db.checkExist(recipeModel.getId())) {
            deleteButton.setVisibility(View.GONE);
        }

        ingredients = recipeModel.getAllIngredients();
        measurements = recipeModel.getAllMeasurements();
        image = recipeModel.getImage();
        instruction = recipeModel.getInstructions();
        name = recipeModel.getRecipeName();

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ingredientsListView.getLayoutParams();
        params.height = 200 * ingredients.size();
        ingredientsListView.setLayoutParams(params);
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(ingredients, measurements);
        ingredientsListView.setAdapter(ingredientsAdapter);
        Log.d(TAG, "onCreate: " + instruction);
        Log.d(TAG, "onCreate: " + ingredients);
        Picasso.get().load(image).into(detailImage);
        instructions.setText(instruction);
        layoutTitle.setText(name);


    }

    public void onClickShare(View view) {
        final String result = "Here is how to make a " + name + " \n" + instruction;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, result);
        String chooseTitle = getString(R.string.chooser);
        Intent chosenIntent = Intent.createChooser(intent, chooseTitle);
        startActivity(chosenIntent);
    }

    public void onClickSave(View view) {
        DatabaseHelper db = new DatabaseHelper(this);
        if (db.checkExist(recipeModel.getId())) {
            Toast.makeText(this, "Already Saved", Toast.LENGTH_SHORT).show();
        } else {
            db.insertRecipe(recipeModel);
            Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickDelete(View view) {
        DatabaseHelper db = new DatabaseHelper(this);
        if (db.checkExist(recipeModel.getId())) {
            db.deleteRecipe(recipeModel);
            Toast.makeText(this, "Recipe Deleted", Toast.LENGTH_SHORT).show();
        }
    }
}