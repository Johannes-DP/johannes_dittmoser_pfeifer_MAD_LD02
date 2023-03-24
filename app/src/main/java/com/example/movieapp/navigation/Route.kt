package com.example.movieapp.navigation

sealed class Route(val route: String) {
    object HomeScreen: Route("home")
    object DetailScreen: Route("details")
    object FavoritesScreen: Route("fav")
}