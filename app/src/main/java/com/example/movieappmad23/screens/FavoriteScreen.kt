package com.example.movieappmad23.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.models.MovieViewModel
import com.example.movieappmad23.models.getMovies
import com.example.movieappmad23.widgets.MovieRow
import com.example.movieappmad23.widgets.SimpleTopAppBar

@Composable
fun FavoriteScreen(navController: NavController,
                   movieViewModel: MovieViewModel
){
    Scaffold(topBar = {
        SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
            Text(text = "My Favorite Movies")
        }
    }){ padding ->
        val movieList: List<Movie> = movieViewModel.movieList

        Column(modifier = Modifier.padding(padding)) {
            LazyColumn {
                items(movieList){
                    if(it.isFavorite){
                        MovieRow(
                            movie = it,
                            onItemClick = { movieId ->
                                navController.navigate(Screen.DetailScreen.withId(movieId))
                            },
                            onFavClick = {movieId -> movieViewModel.markFavorite(movieId)}
                        )
                    }
                }
            }
        }
    }
}