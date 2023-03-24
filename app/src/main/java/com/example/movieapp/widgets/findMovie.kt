package com.example.movieapp.widgets

import androidx.compose.runtime.Composable
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies

@Composable
fun findMovie(movieId: String?): Movie {
    val movies = getMovies()
    var movie = movies[0]
    for (item: Movie in movies) {
        if (movieId == item.id) {
            MovieRow(movie = item)
            movie = item
        }
    }
    return movie
}