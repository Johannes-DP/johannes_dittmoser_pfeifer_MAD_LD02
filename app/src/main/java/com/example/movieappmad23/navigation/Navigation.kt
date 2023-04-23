package com.example.movieappmad23.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad23.data.MovieDatabase
import com.example.movieappmad23.repositories.MovieRepository
import com.example.movieappmad23.screens.*
import com.example.movieappmad23.viewmodels.*

@Composable
fun Navigation() {
    val navController = rememberNavController()

    // inside a composable
    val db = MovieDatabase.getDatabase(LocalContext.current)
    val repository = MovieRepository(movieDao = db.movieDao())

    val movieFactory = MoviesViewModelFactory(repository)
    val addFactory = AddViewModelFactory(repository)
    val favoriteFactory = FavoritesViewModelFactory(repository)
    val detailFactory = DetailViewModelFactory(repository)

    val movieViewModel: MoviesViewModel = viewModel(factory = movieFactory)
    val addViewModel: AddViewModel = viewModel(factory = addFactory)
    val favoritesViewModel: FavoritesViewModel = viewModel(factory = favoriteFactory)
    val detailViewModel: DetailViewModel = viewModel(factory = detailFactory)

    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route){
            HomeScreen(navController = navController, moviesViewModel = movieViewModel)
        }

        composable(Screen.FavoriteScreen.route) {
            FavoriteScreen(navController = navController, favoritesViewModel = favoritesViewModel)
        }
        
        composable(Screen.AddMovieScreen.route) {
            AddMovieScreen(navController = navController, addViewModel = addViewModel)
        }

        // build a route like: root/detail-screen/id=34
        composable(
            Screen.DetailScreen.route,
            arguments = listOf(navArgument(name = DETAIL_ARGUMENT_KEY) {type = NavType.StringType})
        ) { backStackEntry ->    // backstack contains all information from navhost
            DetailScreen(navController = navController,
                detailViewModel = detailViewModel,
                movieId = backStackEntry.arguments?.getString(DETAIL_ARGUMENT_KEY))   // get the argument from navhost that will be passed
        }
    }
}