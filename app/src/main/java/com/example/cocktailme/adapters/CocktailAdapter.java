package com.example.cocktailme.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktailme.R;
import com.example.cocktailme.db.RecipeModel;
import com.example.cocktailme.models.Cocktails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CocktailAdapter extends RecyclerView.Adapter<CocktailAdapter.ViewHolder>{

    Context context;
    List<Cocktails> cocktails;
    TextView title;
    TextView recipeDesc;
    ImageView ivCocktail;
    private List<RecipeModel> recipeModels = new ArrayList<>();
    private CocktailAdapter.OnRecipeListener mOnRecipeListener;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;
        public ImageView imageView;
        public TextView category;
        CocktailAdapter.OnRecipeListener onRecipeListener;
        public ViewHolder(View v, CocktailAdapter.OnRecipeListener onRecipeListener) {
            super(v);
            textView = (TextView) v.findViewById(R.id.layout_recipe_title);
            imageView = (ImageView) v.findViewById(R.id.cocktailImage);
            category = v.findViewById(R.id.category);
            this.onRecipeListener = onRecipeListener;
            v.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            onRecipeListener.onRecipeClick(getAdapterPosition());
        }
    }
    public CocktailAdapter(List<RecipeModel> newRecipeModels, CocktailAdapter.OnRecipeListener onRecipeListener) {
        recipeModels.clear();
        if (newRecipeModels != null){
            recipeModels.addAll(newRecipeModels);
            this.mOnRecipeListener = onRecipeListener;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("CocktailAdapter", "onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        CocktailAdapter.ViewHolder vh = new CocktailAdapter.ViewHolder(v, mOnRecipeListener);
        return vh;
    }
    @Override
    public void onBindViewHolder (@NonNull ViewHolder holder,int position){
        RecipeModel currentRecipeModel = recipeModels.get(position);
        holder.textView.setText(currentRecipeModel.getRecipeName());
        Picasso.get().load(currentRecipeModel.getImage()).into(holder.imageView);
        holder.category.setText(currentRecipeModel.getCategory());
    }

    @Override
    public int getItemCount () {
                return recipeModels.size();
            }

    public interface OnRecipeListener {
        void onRecipeClick(int position);
        void onHorizontalRecipeClick(int position);
    }
}
