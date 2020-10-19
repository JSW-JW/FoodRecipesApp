package com.codingwithmitch.foodrecipes.requests;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.codingwithmitch.foodrecipes.AppExecutors;
import com.codingwithmitch.foodrecipes.models.Recipe;
import com.codingwithmitch.foodrecipes.utils.Constants;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class RecipeApiClient {

    private static RecipeApiClient instance;
    private MutableLiveData<List<Recipe>> mRecipes;

    public static RecipeApiClient getInstance(){
        if(instance == null) {
            instance = new RecipeApiClient();
        }
        return instance;
    }

    private RecipeApiClient(){
        mRecipes = new MutableLiveData<>();
    }

    public MutableLiveData<List<Recipe>> getRecipes(){
        return mRecipes;
    }

    public void searchRecipesApi(){
        final Future handler = AppExecutors.getInstance().NetworkIO().submit(new Runnable() {
            @Override
            public void run() {
                // retrieve data from rest api
                // mRecipes.postValue();
            }
        });

        AppExecutors.getInstance().NetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // let the user know it's timed out.
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }
}
