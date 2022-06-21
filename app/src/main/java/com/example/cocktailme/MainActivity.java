package com.example.cocktailme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestHeaders;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.cocktailme.R;
import com.example.cocktailme.models.Ingredients;
import com.parse.ParseObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
import okhttp3.internal.http2.Header;

public class MainActivity extends AppCompatActivity {

    MultiAutoCompleteTextView multiAutoCompleteTextViewDefault;
    String[] fewRandomSuggestedText = {"Vodka", "Gin", "Rum", "Tequila", "Scotch",
            "Absolut Kurant", "Absolut Peppar", "Absolut Vodka", "Advocaat", "Aejo Rum",
            "Aftershock", "Agave Syrup", "Ale", "Allspice", "Almond Flavoring", "Almond", "Amaretto", "Angelica Root",
            "Angostura Bitters", "Anis", "Anise", "Anisette", "Aperol", "Apfelkorn", "Apple Brandy",
            "Apple Cider", "Apple Juice", "Apple Schnapps", "Apple", "Applejack", "Apricot Brandy", "Apricot Nectar",
            "Apricot", "Aquavit", "Asafoetida", "Añejo Rum", "Bacardi Limon", "Bacardi", "Baileys Irish Cream",
            "Banana Liqueur", "Banana Rum", "Banana Syrup", "Banana", "Barenjager", "Basil", "Beef Stock",
            "Beer", "Benedictine", "Berries", "Bitter lemon", "Bitters", "Black Pepper", "Black Rum", "Black Sambuca",
            "Blackberries", "Blackberry Brandy", "Blackberry Schnapps", "Blackcurrant Cordial", "Blackcurrant Schnapps",
            "Blackcurrant Squash", "Blended Whiskey", "Blue Curacao", "Blue Maui", "Blueberries", "Blueberry Scnapps"
            , "Bourbon", "Brandy", "Brown Sugar", "Butter", "Butterscotch Schnapps", "Cachaca", "Calvados",
            "Campari", "Canadian Whisky", "Candy", "Cantaloupe", "Caramel Coloring", "Carbonated Soft Drink", "Carbonated Water",
            "Cardamom", "Cayenne Pepper", "Celery Salt", "Celery", "Chambord Raspberry Liqueur", "Champagne",
            "Cherries", "Cherry Brandy", "Cherry Cola", "Cherry Grenadine", "Cherry Heering", "Cherry Juice",
            "Cherry Liqueur", "Cherry", "Chocolate Ice-cream", "Chocolate Liqueur", "Chocolate Milk", "Chocolate Syrup",
            "Chocolate", "Cider", "Cinnamon Schnapps", "Cinnamon", "Citrus Vodka", "Clamato Juice", "Cloves",
            "Club soda", "Coca-Cola", "Cocktail Onion"};


    // This is the second one and required for custom features

    // As a sample, few text are given below which can be populated in dropdown, when user starts typing
    // For example, when user types "a", text whichever starting with "a" will be displayed in dropdown
    // As we are using two MultiAutoCompleteTextView components, using two string array separately
//
//    public static List<Ingredients> fromJsonArray(JsonHttpResponseHandler.JSON cocktailJson) throws JSONException {
//        JSONObject jsonObject = cocktailJson.jsonObject;
//        JSONArray jsonIngredients = jsonObject.getJSONArray("ingredients");
//        JSONObject positionZeroJsonObject = jsonIngredients.getJSONObject(0);
//        String individualIngredient = positionZeroJsonObject.getString("ingredient");
//        List<Ingredients> ingredients = new ArrayList<>();
//        for (int i = 1; i < 615; i++) {
//            AsyncHttpClient client = new AsyncHttpClient();
//            client.get(i);
//        }
//        return ingredients;
//    }


    public static final String INGREDIENT_LIST_URL = "https://the-cocktail-db.p.rapidapi.com/filter.php";
    public static final String TAG = "MainActivity";
    ImageButton settingsButton;
    Button generateButton;
    String insertedIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
        ArrayAdapter<String> randomArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fewRandomSuggestedText);
        multiAutoCompleteTextViewDefault.setAdapter(randomArrayAdapter);

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
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
        finish();
    }

    private void goRecipesActivity() {
        Log.d("key", "made");
        Intent i = new Intent(this, RecipesActivity.class);
        i.putExtra("search", insertedIngredients);
        startActivity(i);
        finish();
    }
}