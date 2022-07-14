package com.example.cocktailme.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktailme.R;
import com.example.cocktailme.db.RecipeModel;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.metrics.Trace;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

    public class CacheAdapter extends RecyclerView.Adapter<CacheAdapter.ViewHolder> {
        private List<RecipeModel> recipeModels = new ArrayList<>();
        private OnRecipeListener mOnRecipeListener;


        public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView textView;
            public ImageView imageView;
            public TextView category;
            OnRecipeListener onRecipeListener;

            public ViewHolder(View v, OnRecipeListener onRecipeListener) {
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

        public CacheAdapter(List<RecipeModel> newRecipeModels, OnRecipeListener onRecipeListener) {

            recipeModels.clear();
            if (newRecipeModels != null){
                recipeModels.addAll(newRecipeModels);
                this.mOnRecipeListener = onRecipeListener;
            }
        }

        @Override
        public CacheAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
            ViewHolder vh = new ViewHolder(v, mOnRecipeListener);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Trace trace = FirebasePerformance.getInstance().newTrace("CacheTrace");
            RecipeModel currentRecipeModel = recipeModels.get(position);
            holder.textView.setText(currentRecipeModel.getRecipeName());
            Picasso.get().load(currentRecipeModel.getImage()).into(holder.imageView);
            holder.category.setText(currentRecipeModel.getCategory());
            trace.stop();
        }

        @Override
        public int getItemCount() {
            return recipeModels.size();
        }

        public interface OnRecipeListener {
            void onRecipeClick(int position);
            void onHorizontalRecipeClick(int position);
        }
    }
