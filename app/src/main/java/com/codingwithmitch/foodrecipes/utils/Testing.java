package com.codingwithmitch.foodrecipes.utils;

import android.util.Log;

import com.codingwithmitch.foodrecipes.models.Recipe;

import java.util.List;

public class Testing {

    public static void printRecipes(List<Recipe> list, String tag){
        for(Recipe recipe : list) {
            Log.d(tag, "printRecipes: " + recipe.getTitle());
        }
    }
}
