package com.codingwithmitch.foodrecipes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codingwithmitch.foodrecipes.models.Recipe;
import com.codingwithmitch.foodrecipes.viewmodels.RecipeListViewModel;
import com.codingwithmitch.foodrecipes.viewmodels.RecipeViewModel;

import org.w3c.dom.Text;

public class RecipeActivity extends BaseActivity {

    private static final String TAG = "RecipeActivity";

    // UI components
    private AppCompatImageView mRecipeImage;
    private TextView mRecipeTitle, mRecipeRank;
    private LinearLayout mRecipeIngredientContainer;
    private ScrollView mScrollView;

    private RecipeViewModel mRecipeViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        mRecipeImage = findViewById(R.id.recipe_image);
        mRecipeTitle = findViewById(R.id.recipe_title);
        mRecipeRank = findViewById(R.id.recipe_social_score);
        mRecipeIngredientContainer = findViewById(R.id.ingredients_container);
        mScrollView = findViewById(R.id.parent);

        mRecipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);

        showProgressBar(true); // show progress bar when query starts.
        subscribeObservers();
        getIncomingIntent();

    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("recipe")) {
            Recipe recipe = getIntent().getParcelableExtra("recipe");
            mRecipeViewModel.searchRecipeById(recipe.getRecipe_id());
        }
    }

    private void subscribeObservers(){
        mRecipeViewModel.getRecipe().observe(this, new Observer<Recipe>() {
            @Override
            public void onChanged(Recipe recipe) {
                if(recipe != null) {
                    if(recipe.getRecipe_id().equals(mRecipeViewModel.getRecipeId())) { // not executed as long as old recipeId not match with new Id.
                        setRecipeProperties(recipe);                                    // executed after a new recipe is retrieved and Ids match.
                    }
                }
            }
        });
    }

    private void setRecipeProperties(Recipe recipe){
        if(recipe != null) {
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Glide.with(this)
                    .setDefaultRequestOptions(requestOptions)
                    .load(recipe.getImage_url())
                    .into(mRecipeImage);

            mRecipeTitle.setText(recipe.getTitle());
            mRecipeRank.setText(String.valueOf(Math.round(recipe.getSocial_rank())));

            mRecipeIngredientContainer.removeAllViews();
            for(String ingredient: recipe.getIngredients()) {
                TextView textView = new TextView(this);
                textView.setText(ingredient);
                textView.setTextSize(15);
                textView.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                mRecipeIngredientContainer.addView(textView);
            }



        }

        showParent();
        showProgressBar(false);
    }

    private void showParent(){
        mScrollView.setVisibility(View.VISIBLE);
    }
}
