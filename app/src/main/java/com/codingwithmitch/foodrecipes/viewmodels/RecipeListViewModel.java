package com.codingwithmitch.foodrecipes.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.codingwithmitch.foodrecipes.models.Recipe;
import com.codingwithmitch.foodrecipes.repositories.RecipeRepository;

import java.util.List;

public class RecipeListViewModel extends ViewModel {

//    private MutableLiveData<List<Recipe>> mRecipes = new MutableLiveData<>();
    private RecipeRepository mRecipeRepository;

    public RecipeListViewModel() {
        mRecipeRepository = RecipeRepository.getInstance();
    }

    public LiveData<List<Recipe>> getRecipes(){
        return mRecipeRepository.getRecipes();
    }

}
