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

## why use SQLite Room Database for cache rather than Retrofit Cache? (for this app)

1. this app is for 'searching' recipe app. the key(url), value(json response) in the Retrofit Cache doesn't fit with this app's user experience.
2. Relatively, the search process of SQLite Room DB is much more powerful in this app. It can show the result that user wants even if the query is not exactly the same using SQLite powerful custom query.
3. The way Glide library caches image is not proper for nice user experience. (show blank image) -> what is the difference from SQLite db cache, then?

I really appreciate for this nice course to Mitch Tabian. A nice tutor of android dev.


