package com.example.cocktailme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cocktailme.models.Ingredients;

import org.parceler.Parcels;

public class RecipeDetailsActivity extends AppCompatActivity {

    Ingredients ingredient;
    TextView recTitle;
    TextView recDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        recTitle = findViewById(R.id.recTitle);
        recDesc = findViewById(R.id.recDesc);

        ingredient = (Ingredients) Parcels.unwrap(getIntent().getParcelableExtra(Ingredients.class.getSimpleName()));

        recTitle.setText(ingredient.getRecipeTitle());

    }
}