package com.example.cocktailme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cocktailme.models.Cocktails;

import org.parceler.Parcels;

public class RecipeDetailsActivity extends AppCompatActivity {

    Cocktails cocktail;
    TextView recipeTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        recipeTitle = findViewById(R.id.recipeTitle);

        cocktail = (Cocktails) Parcels.unwrap(getIntent().getParcelableExtra(Cocktails.class.getSimpleName()));

        recipeTitle.setText(cocktail.getRecipeTitle());

    }
}