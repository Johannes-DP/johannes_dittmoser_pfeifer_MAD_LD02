package com.example.movieapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.movieapp.menu.TopAppBar
import com.example.movieapp.widgets.MovieRow
import com.example.movieapp.widgets.findMovie

@Composable
fun DetailScreen(navController: NavController, movieId: String?){
    val movie = findMovie(movieId)
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ){
        Column() {
            TopAppBar(navController, movie.title)
            MovieRow(movie)
            LazyRow{
                val pictures = movie.images
                items(pictures){image ->
                    Card(shape = RoundedCornerShape(10.dp), modifier = Modifier
                        .padding(7.dp)
                        .fillMaxWidth()) {
                     AsyncImage(model = image,
                                contentDescription = "Picture of Film",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                     )}

                }
            }
        }
    }
}