package com.example.movieappmad23.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.repositories.MovieRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

// inherit from ViewModel class
class MoviesViewModel(private val repository: MovieRepository): ViewModel() {
    private val _movieListState = MutableStateFlow(listOf<Movie>())
    val movieListState: StateFlow<List<Movie>> = _movieListState.asStateFlow()
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

    suspend fun deleteMovie(movie: Movie){
        repository.delete(movie)
    }
}