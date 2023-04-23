package com.example.movieappmad23.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.repositories.MovieRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

// inherit from ViewModel class
class DetailViewModel(private val repository: MovieRepository): ViewModel() {
    private val _movieListState = MutableStateFlow(listOf<Movie>())
    init {
        viewModelScope.launch {
            repository.getAllMovies().collect { movieList  ->
                    _movieListState.value = movieList
            }
        }
    }

    suspend fun updateFavoriteMovies(movie: Movie){
        movie.isFavorite = !movie.isFavorite
        repository.update(movie)
    }

    suspend fun getMovieById(id: String): Movie{
        return repository.getMovieById(id)
    }
}