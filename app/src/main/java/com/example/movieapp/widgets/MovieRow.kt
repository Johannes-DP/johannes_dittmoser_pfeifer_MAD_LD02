package com.example.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp.models.Movie

@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit = {}){

    var image : ImageVector
    var expand by remember{
        mutableStateOf(false)
    }

    Card(shape = RoundedCornerShape(8.dp), modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable { onItemClick(movie.id) }
    ) {
        Column {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)) {
                AsyncImage(
                    model = movie.images[0],
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.TopEnd
                ){
                    Icon(imageVector = Icons.Default.FavoriteBorder, tint = Color.White, contentDescription = "favourite", modifier = Modifier
                        .padding(7.dp))
                }
            }
            Row(Modifier.background(color = MaterialTheme.colors.secondary)) {
                Column {
                    Text(movie.title)
                }
                Column(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.End)
                {
                    image = if (expand) {
                        Icons.Default.KeyboardArrowUp
                    } else {
                        Icons.Default.KeyboardArrowDown
                    }

                    Icon(imageVector = image, contentDescription = "dropdown",
                        modifier = Modifier
                            .clickable(onClick = {
                                expand = !expand
                            })
                    )

                }
            }

            Row {
                AnimatedVisibility(visible = expand) {
                    Column {

                        Text("Director: " + movie.director)
                        Text("Release Year: " + movie.year)
                        Text("Genre: " + movie.genre)
                        Text("Actors: " + movie.actors)
                        Text("Plot: " + movie.plot)
                        Text("Rating: " + movie.rating)
                    }
                }
            }
        }
    }
}
