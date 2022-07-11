package com.example.cocktailme.adapters;

import android.content.Context;
import android.content.Intent;
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

    public CocktailAdapter(Context context, List<RecipeModel> cocktails) {
        this.context = context;
        this.recipeModels = cocktails;
    }
    //usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("CocktailAdapter", "onCreateViewHolder");
        View cocktailView = LayoutInflater.from(context).inflate(R.layout.item_recipe,parent, false);
        return new ViewHolder(cocktailView);
    }
    @Override
    public void onBindViewHolder (@NonNull ViewHolder holder,int position){
        RecipeModel currentRecipeModel = recipeModels.get(position);
        holder.title.setText(currentRecipeModel.getRecipeName());
        Picasso.get().load(currentRecipeModel.getImage()).into(holder.ivCocktail);
        holder.recipeDesc.setText(currentRecipeModel.getCategory());
            }

            @Override
            public int getItemCount () {
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
            // gets item position
            int position = getAdapterPosition();
            // make sure the position is valid, i.e. actually exists in the view
            if (position != RecyclerView.NO_POSITION) {
                // get the movie at the position, this won't work if the class is static
                RecipeModel ingredient = recipeModels.get(position);
                // create intent for the new activity
                Intent intent = new Intent(context, RecipeDetailsActivity.class);
                // serialize the movie using parceler, use its short name as a key
                intent.putExtra(RecipeModel.class.getName(), ingredient);
                // show the activity
                context.startActivity(intent);
            }
        }
    }
    }
    //test



