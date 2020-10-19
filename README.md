This repo is for practicing how to use "REST API with Retrofit" & how to optimize app performance.

## Concepts that are used for optimization in this App

* LiveData
* Pagination
* Database Cache
* (Data binding) planning to be updated later if necessary

## understanding MVVM Pathway in this project

1. LiveData Observer in the Activity / (Fragment).
2. Process of retrieving livedata is started from the function call in activity / fragment. (searchRecipeApi).
3. searchRecipeApi method pass through from 'Activity' -> 'ViewModel' -> 'Repository' -> 'ApiClient'.
4. Retrieved data(REST Api response data) in the RecipeApiClient Class is retrieved from 'ApiClient' -> 'Repository' -> 'ViewModel' -> 'Activity'.

* + In the ApiClient, AppExecutor is used to make the work done in background thread.
* + RetrieveRecipesRunnable Class is used to submit it to AppExecutor.

I really appreciate for this nice course to Mitch Tabian. A nice tutor of android dev.


