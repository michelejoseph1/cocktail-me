package com.example.cocktailme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cocktailme.models.Cocktails;

import org.parceler.Parcels;

public class RecipeDetailsActivity extends AppCompatActivity {

    Cocktails ingredient;
    TextView recTitle, recDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        recTitle = findViewById(R.id.recTitle);
        recDesc = findViewById(R.id.recDesc);

        ingredient = (Cocktails) Parcels.unwrap(getIntent().getParcelableExtra(Cocktails.class.getSimpleName()));

        recTitle.setText(ingredient.getRecipeTitle());

    }
}