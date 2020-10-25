package com.codingwithmitch.foodrecipes.repositories;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.codingwithmitch.foodrecipes.models.Recipe;
import com.codingwithmitch.foodrecipes.requests.RecipeApiClient;

import java.util.List;

public class RecipeRepository {

    private static RecipeRepository instance;
    private RecipeApiClient mRecipeApiClient;
    private String mQuery;
    private String mRecipeId;
    private int mPageNumber;
    private MutableLiveData<Boolean> mIsQueryExhausted = new MutableLiveData<>();
    private MediatorLiveData<List<Recipe>> mRecipes = new MediatorLiveData<>();

    public static RecipeRepository getInstance() {
        if (instance == null) {
            instance = new RecipeRepository();
        }
        return instance;
    }

    private RecipeRepository() {
        mRecipeApiClient = RecipeApiClient.getInstance();
        initMediators();
    }

    private void initMediators() {
        MutableLiveData<List<Recipe>> recipeListApiSource = mRecipeApiClient.getRecipes();
        mRecipes.addSource(recipeListApiSource, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                if (recipes != null) {
                    mRecipes.setValue(recipes);
                    doneQuery(recipes);
                } else {
                    // implement database cache.
                    doneQuery(null);
                }
            }
        });
    }

    public MutableLiveData<Boolean> isQueryExhausted(){
        return mIsQueryExhausted;
    }

    private void doneQuery(List<Recipe> list) {
        if (list != null) {
            if (list.size() < 30) {
                mIsQueryExhausted.setValue(true);
            }
        }
        else {
            mIsQueryExhausted.setValue(true);
        }
    }

    public MutableLiveData<List<Recipe>> getRecipes() {
        return mRecipeApiClient.getRecipes();
    }

    public MutableLiveData<Recipe> getRecipe() {
        return mRecipeApiClient.getRecipe();
    }

    public MutableLiveData<Boolean> isRecipeRequestTimedOut() {
        return mRecipeApiClient.isRecipeRequestTimedOut();
    }

    public void searchRecipesApi(String query, int pageNumber) {
        if (pageNumber == 0) {
            pageNumber = 1;
        }
        mIsQueryExhausted.setValue(false); // reset the flag to false every searchRecipeApi called.
        mQuery = query;
        mPageNumber = pageNumber;
        mRecipeApiClient.searchRecipesApi(query, pageNumber);
    }

    public void searchRecipeById(String recipeId) {
        mRecipeId = recipeId;
        mRecipeApiClient.searchRecipeById(recipeId);
    }

    public void searchNextPage() {
        searchRecipesApi(mQuery, mPageNumber + 1);
    }

    public void cancelRequest() {
        mRecipeApiClient.cancelRequest();
    }
}
