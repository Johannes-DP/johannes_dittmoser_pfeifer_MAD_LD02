package com.example.movieappmad23.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.repositories.MovieRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

// inherit from ViewModel class
class FavoritesViewModel(private val repository: MovieRepository): ViewModel() {
    private val _favoriteListState = MutableStateFlow(listOf<Movie>())
    val favoriteMovieState: StateFlow<List<Movie>> = _favoriteListState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllFavorites().collect { movieList  ->
                if(!movieList.isNullOrEmpty()) {
                    _favoriteListState.value = movieList
                }
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