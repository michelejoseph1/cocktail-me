package com.example.cocktailme;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.example.cocktailme.fragments.ProfileFragment;
import com.example.cocktailme.fragments.HomeFragment;
import com.example.cocktailme.fragments.SettingsFragment;
import com.example.cocktailme.models.Cocktails;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CocktailNamesActivity extends AppCompatActivity {
    public AsyncHttpClient client;
    String insertedIngredients;
    String cocktailTitle;
    int cocktailID;
    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        Cocktails cocktail = new Cocktails();
        cocktailTitle = cocktail.getRecipeTitle();
        cocktailID = cocktail.getID();
        Log.d("checking to confirm ID", "Results: " + cocktailID);
        client = new AsyncHttpClient();
        insertedIngredients = getIntent().getStringExtra("search");


        bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_settings:
                        fragment = new SettingsFragment();

                        break;
                    case R.id.action_profile:
                        fragment = new ProfileFragment();
                        break;

                    default:
                        HomeFragment tempFragment = new HomeFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("search", insertedIngredients);
                        tempFragment.setArguments(bundle);
                        fragment = tempFragment;
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }
}