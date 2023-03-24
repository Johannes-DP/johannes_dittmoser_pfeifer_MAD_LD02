package com.example.movieapp.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.menu.TopAppBar
import com.example.movieapp.navigation.Route
import com.example.movieapp.widgets.MovieRow


@Composable
fun HomeScreen(navController: NavController){
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ){
        Column() {
            TopAppBar(navController)
            MyList(navController)
        }
    }
}

@Composable
fun MyList(navController: NavController = rememberNavController(), movies: List <Movie> = getMovies()){

    LazyColumn{
        items(movies){movie ->
            MovieRow(movie = movie){movieId -> Log.d("MyList","item clicked $movieId")
            navController.navigate(Route.DetailScreen.route + "/$movieId")
            }
        }
    }
}

