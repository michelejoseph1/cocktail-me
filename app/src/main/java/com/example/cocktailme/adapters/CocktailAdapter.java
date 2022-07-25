package com.example.cocktailme.adapters;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktailme.R;
import com.example.cocktailme.RecipeDetailsActivity;
import com.example.cocktailme.db.RecipeModel;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.metrics.Trace;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CocktailAdapter extends RecyclerView.Adapter<CocktailAdapter.ViewHolder> {

    Context context;
    private List<RecipeModel> recipeModels = new ArrayList<>();

    public CocktailAdapter(Context context, List<RecipeModel> cocktails) {
        this.context = context;
        this.recipeModels = cocktails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("CocktailAdapter", "onCreateViewHolder");
        View cocktailView = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false);
        return new ViewHolder(cocktailView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Trace trace = FirebasePerformance.getInstance().newTrace("TimeToLoadRecipeFromAPI");
        RecipeModel currentRecipeModel = recipeModels.get(position);
        holder.title.setText(currentRecipeModel.getRecipeName());
        Picasso.get().load(currentRecipeModel.getImage()).into(holder.ivCocktail);
        holder.recipeDesc.setText(currentRecipeModel.getCategory());
        trace.stop();
    }

    @Override
    public int getItemCount() {
        return recipeModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView recipeDesc;
        ImageView ivCocktail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            recipeDesc = itemView.findViewById(R.id.recipeDesc);
            ivCocktail = itemView.findViewById(R.id.ivCocktail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                RecipeModel ingredient = recipeModels.get(position);
                Bundle bundlanimation = ActivityOptions.makeCustomAnimation(context, R.anim.fade_in,R.anim.fade_out).toBundle();
                Intent intent = new Intent(context, RecipeDetailsActivity.class);
                intent.putExtra(RecipeModel.class.getName(), ingredient);
                context.startActivity(intent, bundlanimation);
            }
        }
    }
}



