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
import com.example.cocktailme.Rating;
import com.example.cocktailme.RecipeDetails;
import com.example.cocktailme.adapters.CacheAdapter;
import com.example.cocktailme.adapters.CocktailAdapter;
import com.example.cocktailme.db.DatabaseHelper;
import com.example.cocktailme.db.RecipeModel;
import com.example.cocktailme.network.RecipeApiService;
import com.example.cocktailme.network.RecipeResponse;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.Collections;
import java.util.Comparator;
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
    private List<RecipeModel> listRecipeModels;
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

        CocktailNamesActivity tempActivity = (CocktailNamesActivity) getActivity();
        cocktailList = tempActivity.cocktails;
        cocktailAdapter = tempActivity.cocktailAdapter;
        recyclerView.setAdapter(cocktailAdapter);
        ParseQuery<Rating> query = ParseQuery.getQuery("Rating");
        query.addAscendingOrder("numStars");
        query.findInBackground(new FindCallback<Rating>() {
            @Override
            public void done(List<Rating> ratings, ParseException e) {
                Log.d("check", "results" + ratings);
                //Log.d(TAG, ratings);
                if (e == null) {
                    if (ratings.size() > 0) {
                        for (Rating rating : ratings) {
                            for(RecipeModel recipeModel: cocktailList) {
                                if(recipeModel.getId() == rating.getCocktailId()){
                                    recipeModel.setRating(rating.getRating());
                                }
                            }
                        }
                        Collections.sort(cocktailList, new Comparator<RecipeModel>() {
                            @Override
                            public int compare(RecipeModel o1, RecipeModel o2) {
                                if(o1.getRating() > o2.getRating()) {
                                    Log.d("unique", "results: " + o1.getRating() + o2.getRating());
                                    return 1;
                                }
                                else if (o1.getRating() < o2.getRating()) {
                                    return -1;
                                }
                                return 0;
                            }
                        });
                        cocktailAdapter.notifyDataSetChanged();
                    }
                }
            }
        });


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
                listRecipeModels = recipeResponse.getRecipeModels();
                if (listRecipeModels != null) {
                    cacheAdapter = new CacheAdapter(listRecipeModels, HomeFragment.this);
                    recyclerView.setAdapter(cacheAdapter);
                }else {
                    cacheAdapter = new CacheAdapter(listRecipeModels, HomeFragment.this);
                    recyclerView.setAdapter(cacheAdapter);
                    Toast.makeText(getActivity(), "Unfortunately, we did not find any recipes containing those ingredients.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Log.i(TAG, "response failed");
            }
        });
    }
    private void fetchRandomRecipes(int nRandom, List<RecipeModel> randomRecipes1, List<RecipeModel> randomRecipes2) {
        if (retrofit == null) {
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
                } else {
                    listRecipeModels = randomRecipes1;
                    cacheAdapter = new CacheAdapter(listRecipeModels, HomeFragment.this);
                    recyclerView.setAdapter(cacheAdapter);
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
        Log.d(TAG, "onRecipeClick: " + listRecipeModels.get(position).getInstructions());
        RecipeModel currentRecipeModel = listRecipeModels.get(position);
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


