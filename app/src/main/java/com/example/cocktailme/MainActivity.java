package com.example.cocktailme;

import static com.example.cocktailme.models.Constants.INGREDIENT_LIST;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.example.cocktailme.db.DatabaseHelper;
import com.parse.ParseObject;

public class MainActivity extends AppCompatActivity {

    MultiAutoCompleteTextView multiAutoCompleteTextViewDefault;

    public static final String INGREDIENT_LIST_URL = "https://the-cocktail-db.p.rapidapi.com/filter.php";
    public static final String TAG = "MainActivity";
    ImageButton settingsButton;
    Button generateButton;
    String insertedIngredients;
    private DatabaseHelper db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        initDatabase();
    }

    private void initDatabase() {
        db = new DatabaseHelper(this);
        if (!db.checkForTableExists("recipes")) {
            db.resetTable();
        }

        generateButton = findViewById(R.id.generateButton);
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertedIngredients = multiAutoCompleteTextViewDefault.getText().toString();
                goRecipesActivity();

            }
        });

        settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSettingsActivity();
            }
        });


        multiAutoCompleteTextViewDefault = findViewById(R.id.multiAutoCompleteTextViewDefault);
        ArrayAdapter<String> randomArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                INGREDIENT_LIST);
        multiAutoCompleteTextViewDefault.setAdapter(randomArrayAdapter);
        Log.d(TAG, "adapter set");

        multiAutoCompleteTextViewDefault.setThreshold(1);

        multiAutoCompleteTextViewDefault.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());


        AsyncHttpClient client = new AsyncHttpClient();


        ParseObject firstObject = new ParseObject("FirstClass");
        firstObject.put("message", "Hey ! First message from android. Parse is now connected");
        firstObject.saveInBackground(e -> {
            if (e != null) {
                Log.e("MainActivity", e.getLocalizedMessage());
            } else {
                Log.d("MainActivity", "Object saved.");
            }
        });
    }

    private void goSettingsActivity() {
        Intent mainToSettings = new Intent(this, SettingsActivity.class);
        startActivity(mainToSettings);
        finish();
    }

    private void goRecipesActivity() {
        Intent mainToRecipes = new Intent(this, CocktailNamesActivity.class);
        mainToRecipes.putExtra("search", insertedIngredients);
        startActivity(mainToRecipes);
        finish();
    }
}