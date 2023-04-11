package com.example.movieappmad23.models

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class MovieViewModel: ViewModel() {
    private val _movieList = getMovies().toMutableStateList()
    val movieList: List<Movie>
        get() = _movieList

    fun findMovie(movieId: String): Movie {
        val movies = getMovies()
        var movie = movies[0]
        for (item: Movie in movies) {
            if (movieId == item.id) {
                movie = item
            }
        }
        return movie
    }

    fun markFavorite(id: String){
        for (movie in _movieList){
            if(movie.id == id){
                movie.isFavorite = !movie.isFavorite
            }
        }
    }

    fun validation(string: String?):Boolean{
        if (string == null || string == ""){
            return false
        }
        return true
    }

    fun validation(float: Float?): Boolean{
        if (float == null || float == -1f){
            return false
        }
        return true
    }

    fun validation(genres: List<ListItemSelectable>): Boolean{
        for (genre in genres)
            if(genre.isSelected){
                return true
            }
        return false
    }

    fun addMovie(title: String, year: String, genres: List<ListItemSelectable>, director:String, actors: String, plot: String, rating: Float){
        var count = 1
        val id = "tt$count"
        val genreList = mutableListOf<Genre>()
        for (genre in genres){
            if(genre.isSelected){
                genreList.add(enumValueOf(genre.title))
            }
        }
        val movie = Movie(id,title, year, genreList, director, actors, plot, images = listOf("https://nachamafrica.org/wp-content/themes/consultix/images/no-image-found-360x260.png"), rating)
        _movieList.add(movie)
        count++
    }
}