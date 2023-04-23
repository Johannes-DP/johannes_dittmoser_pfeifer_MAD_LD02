package com.example.movieappmad23.data

import androidx.room.*
import com.example.movieappmad23.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert
    suspend fun add (movie: Movie)

    @Update
    suspend fun update (movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT * from movie")
    fun readAll(): Flow<List<Movie>>

    @Query("SELECT * from movie WHERE isFavorite = 1")
    fun readAllFavorites(): Flow<List<Movie>>

    @Query("SELECT * from movie WHERE id=:id")
    suspend fun getMovieById(id: String): Movie

}