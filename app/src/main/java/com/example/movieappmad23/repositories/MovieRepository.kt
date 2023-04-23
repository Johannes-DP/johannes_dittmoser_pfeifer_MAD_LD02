package com.example.movieappmad23.repositories

import com.example.movieappmad23.data.MovieDao
import com.example.movieappmad23.models.Movie

class MovieRepository(private val movieDao: MovieDao) {

    suspend fun add(movie: Movie) = movieDao.add(movie)

    suspend fun update(movie: Movie) = movieDao.update(movie)

    suspend fun delete(movie: Movie) = movieDao.delete(movie)

    fun getAllMovies() = movieDao.readAll()

    fun getAllFavorites() = movieDao.readAllFavorites()

    suspend fun getMovieById(movieId: String): Movie = movieDao.getMovieById(movieId)

}