package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.screens.DetailScreen
import com.example.movieapp.screens.HomeScreen
import com.example.movieapp.screens.FavoriteScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home"){
        composable(route = Route.HomeScreen.route){
            HomeScreen(navController)
        }

        composable(
            route = Route.DetailScreen.route + "/{movieId}",
            arguments = listOf(navArgument("movieId"){
                type = NavType.StringType
            })
        ) {navBackStackEntry ->
            DetailScreen(navController, movieId = navBackStackEntry.arguments?.getString("movieId"))
        }
        composable(route = Route.FavoritesScreen.route){
                FavoriteScreen(navController)
            }
    }
}