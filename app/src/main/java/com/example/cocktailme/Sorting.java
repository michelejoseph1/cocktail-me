package com.example.cocktailme;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Sorting {

    ArrayList<String> highestRatedCocktails;
    ArrayAdapter arrayAdapter;

    highestRatedCocktails = new ArrayList<String>();
    arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, highestRatedCocktails);
    }