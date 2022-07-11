package com.example.cocktailme.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktailme.CocktailNamesActivity;
import com.example.cocktailme.R;
import com.example.cocktailme.RecipeDetails;
import com.example.cocktailme.adapters.CacheAdapter;
import com.example.cocktailme.adapters.CocktailAdapter;
import com.example.cocktailme.db.DatabaseHelper;
import com.example.cocktailme.db.RecipeModel;
import com.example.cocktailme.network.RecipeApiService;
import com.example.cocktailme.network.RecipeResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment implements CacheAdapter.OnRecipeListener {
    public static final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/";
    private static final String TAG = "RecipeListFragment";

    private FrameLayout fragmentContainer;
    private RecyclerView recyclerView;
    private RecyclerView horizontalScrollView;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseHelper db;
    private CardView cardView;
    private EditText editQuery;
    private TextView tabTitle;
    private Retrofit retrofit = null;
    private List<RecipeModel> verticalRecipeModels;
    private List<RecipeModel> horizontalRecipeModels;
    private LinearLayoutManager horizontalLayout;
    private TextView iconic;
    private View viewId;
    CocktailAdapter cocktailAdapter;
    List<RecipeModel> cocktailList;

    CacheAdapter cacheAdapter;

    public static HomeFragment newInstance(int index) {
        HomeFragment fragment = new HomeFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        db = new DatabaseHelper(getActivity());
        View view = inflater.inflate(R.layout.fragment_home, container, false);

return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentContainer = view.findViewById(R.id.fragment_container);
        recyclerView = view.findViewById(R.id.fragment_recycler_view);
        cardView = view.findViewById(R.id.card_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        iconic = view.findViewById(R.id.iconic);
        viewId = view.findViewById(R.id.view);

        tabTitle = view.findViewById(R.id.tabTitle);
        editQuery = (EditText) view.findViewById(R.id.edit_query);
        CocktailNamesActivity tempActivity = (CocktailNamesActivity) getActivity();
        cocktailList = tempActivity.cocktails;
        cocktailAdapter = tempActivity.cocktailAdapter;
        recyclerView.setAdapter(cocktailAdapter);


    }

    private void initMyRecipe(View view) {
        tabTitle.setText(R.string.tab3Title);
        editQuery.setVisibility(View.GONE);
        iconic.setVisibility(View.GONE);
        refreshDatabase();
    }

    public void refreshDatabase() {
        List<RecipeModel> recipeModelDatabase = new ArrayList<>();
        recipeModelDatabase.addAll(db.getAllRecipes());
        for (RecipeModel recipeModelDB : recipeModelDatabase) {
            int id = recipeModelDB.getId();
        }
        verticalRecipeModels = recipeModelDatabase;
        cacheAdapter = new CacheAdapter(verticalRecipeModels, this);
        recyclerView.setAdapter(cacheAdapter);
    }

    private void fetchRecipes(String query) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        RecipeApiService recipeApiService = retrofit.create(RecipeApiService.class);
        Call<RecipeResponse> call = recipeApiService.getRecipes(query);
        call.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                RecipeResponse recipeResponse = response.body();
                verticalRecipeModels = recipeResponse.getRecipeModels();
                if (verticalRecipeModels != null) {
                    cacheAdapter = new CacheAdapter(verticalRecipeModels, HomeFragment.this);
                    recyclerView.setAdapter(cacheAdapter);
                }else {
                    cacheAdapter = new CacheAdapter(verticalRecipeModels, HomeFragment.this);
                    recyclerView.setAdapter(cacheAdapter);
                    Toast.makeText(getActivity(), "No Result Found", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Log.i("failed:", "11111111111111");
            }
        });
    }


    private void fetchRandomRecipes(int nRandom, List<RecipeModel> randomRecipes1, List<RecipeModel> randomRecipes2) {
        Log.i("random fetched:", "111111111");
        if (retrofit == null) {
            Log.i("fetched:", "retrofit = null");
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        RecipeApiService recipeApiService = retrofit.create(RecipeApiService.class);
        Call<RecipeResponse> call = recipeApiService.getRandomRecipes();
        call.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                Log.i("random_responded:", response.body().toString());
                RecipeResponse recipeResponse = response.body();
                if (nRandom < 10) {
                    randomRecipes1.addAll(recipeResponse.getRecipeModels());
                } else if (nRandom >= 10 && nRandom < 20) {
                    randomRecipes2.addAll(recipeResponse.getRecipeModels());
                }
                if (nRandom < 20) {
                    fetchRandomRecipes(nRandom + 1, randomRecipes1, randomRecipes2);
                }else {
                    verticalRecipeModels = randomRecipes1;
                    cacheAdapter = new CacheAdapter(verticalRecipeModels, HomeFragment.this);
                    recyclerView.setAdapter(cacheAdapter);
                    horizontalRecipeModels = randomRecipes2;
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Log.i("failed:", "11111111111111");
            }
        });
    }

    public void refresh() {
        if (getArguments().getInt("index", 0) > -1 && recyclerView != null) {
            recyclerView.smoothScrollToPosition(0);
        }
    }


    @Override
    public void onRecipeClick(int position) {
        Log.d(TAG, "onRecipeClick: " + position);
        Log.d(TAG, "onRecipeClick: " + verticalRecipeModels.get(position).getInstructions());
        RecipeModel currentRecipeModel = verticalRecipeModels.get(position);
        Intent intent = new Intent(getActivity(), RecipeDetails.class);
        intent.putExtra("currRecipe", currentRecipeModel);
        startActivity(intent);
    }

    @Override
    public void onHorizontalRecipeClick(int position) {
        Log.d(TAG, "onHorizontalRecipeClick: ");
        RecipeModel currentRecipeModel = horizontalRecipeModels.get(position);
        Intent intent = new Intent(getActivity(), RecipeDetails.class);
        intent.putExtra("currRecipe", currentRecipeModel);
        startActivity(intent);
    }

}


