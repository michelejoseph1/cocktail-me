package com.example.cocktailme;

<<<<<<< HEAD
import static com.example.cocktailme.models.Constants.INGREDIENT_LIST;
=======
import static com.example.cocktailme.models.Constants.fewRandomSuggestedText;

>>>>>>> 72cc745 (fixing after PR comments)
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
import com.example.cocktailme.models.Constants;
import com.parse.ParseObject;

public class MainActivity extends AppCompatActivity {

    MultiAutoCompleteTextView multiAutoCompleteTextViewDefault;
<<<<<<< HEAD
<<<<<<< HEAD
=======
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
            "Club soda", "Coca-Cola", "Cocktail Onion", "Cocoa Powder", "Coconut Cream", "Coconut Liqueur", "Coconut Milk", "Coconut Rum",
            "Coconut Syrup", "Coffee Brandy", "Coffee Liqueur", "Coffee", "Cognac", "Cointreau", "Cola",
            "Cold Water", "Condensed Milk", "Coriander", "Corn Syrup", "Cornstarch", "Corona", "Cranberries",
            "Cranberry Juice", "Cranberry Liqueur", "Cranberry Vodka", "Cream of Coconut", "Cream Sherry",
            "Cream Soda", "Cream", "Creme De Almond", "Creme De Banane", "Creme De Cacao", "Creme De Cassis",
            "Creme De Noyaux", "Creme Fraiche", "Crown Royal", "Crystal Light", "Cucumber", "Cumin Powder",
            "Cumin Seed", "Curacao", "Cynar", "Daiquiri Mix", "Dark Chocolate", "Dark Creme De Cacao", "Dark Rum",
            "Dark Soy Sauce", "Demerara Sugar", "Dr. Pepper", "Drambuie", "Dried Oregano", "Dry Vermouth",
            "Dubonnet Blanc", "Dubonnet Rouge", "Egg White", "Egg Yolk", "Egg", "Eggnog", "Erin Cream",
            "Espresso", "Everclear", "Fanta", "Fennel Seeds", "Firewater", "Flaked Almonds", "Food Coloring",
            "Forbidden Fruit", "Frangelico", "Fresca", "Fresh Basil", "Fresh Lemon Juice", "Fruit Juice",
            "Fruit Punch", "Fruit", "Galliano", "Garlic Sauce", "Gatorade", "Ginger Ale", "Ginger Beer",
            "Ginger", "Glycerine", "Godiva Liqueur", "Gold rum", "Gold Tequila", "Goldschlager", "Grain Alcohol",
            "Grand Marnier", "Granulated Sugar", "Grape juice", "Grape soda", "Grapefruit Juice", "Grapes",
            "Green Chartreuse", "Green Creme de Menthe", "Green Ginger Wine", "Green Olives", "Grenadine",
            "Ground Ginger", "Guava juice", "Guinness stout", "Guinness", "Half-and-half", "Hawaiian punch",
            "Hazelnut liqueur", "Heavy cream", "Honey", "Hooch", "Hot Chocolate", "Hot Damn", "Hot Sauce",
            "Hpnotiq", "Ice-Cream", "Ice", "Iced tea", "Irish cream", "Irish Whiskey", "Jack Daniels",
            "Jello", "Jelly", "Jagermeister", "Jim Beam", "Johnnie Walker", "Kahlua", "Key Largo Schnapps",
            "Kirschwasser", "Kiwi liqueur", "Kiwi", "Kool-Aid", "Kummel", "Lager", "Lemon Juice",
            "Lemon Peel", "Lemon soda", "Lemon vodka", "Lemon-lime soda", "lemon-lime", "lemon", "Lemonade",
            "Licorice Root", "Light Cream", "Light Rum", "Lillet", "Lime juice cordial", "Lime Juice",
            "Lime liqueur", "Lime Peel", "Lime vodka", "Lime", "Limeade", "Madeira", "Malibu Rum", "Mandarin",
            "Mandarine napoleon", "Mango", "Maple syrup", "Maraschino cherry juice", "Maraschino Cherry",
            "Maraschino Liqueur", "Margarita mix", "Marjoram leaves", "Marshmallows", "Maui",
            "Melon Liqueur", "Melon Vodka", "Mezcal", "Midori Melon Liqueur", "Midori", "Milk", "Mint syrup",
            "Mint", "Mountain Dew", "Nutmeg", "Olive Oil", "Olive", "Onion", "Orange Bitters", "Orange Curacao",
            "Orange Juice", "Orange liqueur", "Orange Peel", "Orange rum","Orange Soda", "Orange spiral",
            "Orange vodka", "Orange", "Oreo cookie", "Orgeat Syrup", "Ouzo", "Oyster Sauce", "Papaya juice",
            "Papaya", "Parfait amour", "Passion fruit juice", "Passoa", "Peach brandy", "Peach juice",
            "Peach liqueur", "Peach Nectar", "Peach Schnapps", "Peach Vodka", "Peach", "Peachtree schnapps",
            "Peanut Oil", "Pepper"

    };
>>>>>>> c68224e (adds cocktail names to RV and adds images with glide)
=======
>>>>>>> 72cc745 (fixing after PR comments)

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
        ArrayAdapter<String> randomArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
<<<<<<< HEAD
                INGREDIENT_LIST);
=======
                fewRandomSuggestedText);
>>>>>>> 72cc745 (fixing after PR comments)
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
<<<<<<< HEAD
<<<<<<< HEAD
        Intent mainToSettings = new Intent(this, SettingsActivity.class);
        startActivity(mainToSettings);
=======
        Intent main_to_settings = new Intent(this, SettingsActivity.class);
        startActivity(main_to_settings);
>>>>>>> 72cc745 (fixing after PR comments)
=======
        Intent mainToSettings = new Intent(this, SettingsActivity.class);
        startActivity(mainToSettings);
>>>>>>> d1da0c8 (fixed variable names for java)
        finish();
    }

    private void goRecipesActivity() {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        Intent mainToRecipes = new Intent(this, RecipesActivity.class);
        mainToRecipes.putExtra("search", insertedIngredients);
        startActivity(mainToRecipes);
=======
        Intent i = new Intent(this, RecipesActivity.class);
        i.putExtra("search", insertedIngredients);
        startActivity(i);
>>>>>>> c68224e (adds cocktail names to RV and adds images with glide)
=======
        Intent main_to_recipes = new Intent(this, RecipesActivity.class);
        main_to_recipes.putExtra("search", insertedIngredients);
        startActivity(main_to_recipes);
>>>>>>> 72cc745 (fixing after PR comments)
=======
        Intent mainToRecipes = new Intent(this, RecipesActivity.class);
        mainToRecipes.putExtra("search", insertedIngredients);
        startActivity(mainToRecipes);
>>>>>>> d1da0c8 (fixed variable names for java)
        finish();
    }
}