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

import com.bumptech.glide.Glide;
import com.example.cocktailme.R;
import com.example.cocktailme.RecipeDetailsActivity;
<<<<<<< HEAD
<<<<<<< HEAD
import com.example.cocktailme.models.Cocktails;
=======
import com.example.cocktailme.RecipesActivity;
import com.example.cocktailme.models.Ingredients;
>>>>>>> c68224e (adds cocktail names to RV and adds images with glide)
=======
import com.example.cocktailme.models.Cocktails;
>>>>>>> 72cc745 (fixing after PR comments)

import org.parceler.Parcels;

import java.util.List;

public class CocktailAdapter extends RecyclerView.Adapter<CocktailAdapter.ViewHolder>{

    Context context;
    List<Cocktails> ingredients;

    public CocktailAdapter(Context context, List<Cocktails> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }
    //usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("CocktailAdapter", "onCreateViewHolder");
        View cocktailView = LayoutInflater.from(context).inflate(R.layout.item_recipe,parent, false);
        return new ViewHolder(cocktailView);
    }
    //involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder " + position);
        //get the movie at the passed in position
        Cocktails ingredient = ingredients.get(position);
        //bind the movie data into the VH
        holder.bind(ingredient);
    }
    //returns the total count of items in the list
    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView recDesc;
        ImageView ivCocktail;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title);
            recDesc = itemView.findViewById(R.id.recipeDesc);
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
                Cocktails ingredient = ingredients.get(position);
                // create intent for the new activity
               Intent intent = new Intent(context, RecipeDetailsActivity.class);
                // serialize the movie using parceler, use its short name as a key
               intent.putExtra(Cocktails.class.getSimpleName(), Parcels.wrap(ingredient));
                // show the activity
              context.startActivity(intent);
            }
        }
        public void bind(Cocktails ingredient) {
            Log.d("CocktailAdapter", "bind reached");
            title.setText(ingredient.getRecipeTitle());
<<<<<<< HEAD
            recipeDesc.setText(ingredient.getRecipeDesc());
=======
            recDesc.setText(ingredient.getRecipeDesc());
>>>>>>> c68224e (adds cocktail names to RV and adds images with glide)
            String imageURL;


            imageURL = ingredient.getCocktailPath();
            Glide.with(context).load(imageURL).into(ivCocktail);
            }

        }
    }