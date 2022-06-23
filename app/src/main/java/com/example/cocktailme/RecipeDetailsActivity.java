package com.example.cocktailme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

<<<<<<< HEAD
<<<<<<< HEAD
import com.example.cocktailme.models.Cocktails;
=======
import com.example.cocktailme.models.Ingredients;
>>>>>>> c68224e (adds cocktail names to RV and adds images with glide)
=======
import com.example.cocktailme.models.Cocktails;
>>>>>>> 72cc745 (fixing after PR comments)

import org.parceler.Parcels;

public class RecipeDetailsActivity extends AppCompatActivity {

<<<<<<< HEAD
<<<<<<< HEAD
    Cocktails cocktail;
    TextView recipeTitle;
=======
    Ingredients ingredient;
    TextView recTitle;
    TextView recDesc;
>>>>>>> c68224e (adds cocktail names to RV and adds images with glide)
=======
    Cocktails ingredient;
    TextView recTitle, recDesc;
>>>>>>> 72cc745 (fixing after PR comments)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

<<<<<<< HEAD
        recipeTitle = findViewById(R.id.recipeTitle);

        cocktail = (Cocktails) Parcels.unwrap(getIntent().getParcelableExtra(Cocktails.class.getSimpleName()));

        recipeTitle.setText(cocktail.getRecipeTitle());
=======
        recTitle = findViewById(R.id.recTitle);
        recDesc = findViewById(R.id.recDesc);

        ingredient = (Cocktails) Parcels.unwrap(getIntent().getParcelableExtra(Cocktails.class.getSimpleName()));

        recTitle.setText(ingredient.getRecipeTitle());
>>>>>>> c68224e (adds cocktail names to RV and adds images with glide)

    }
}