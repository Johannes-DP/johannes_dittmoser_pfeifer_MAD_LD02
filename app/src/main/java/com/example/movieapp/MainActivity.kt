package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.movieapp.ui.theme.MovieAppTheme
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import androidx.compose.runtime.remember as remember
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        TopBarApp()
                        MovieList(getMovies())
                    }
                }
            }
        }
    }
}

@Composable
fun TopBarApp(){
    var menu by remember{
        mutableStateOf(false)
    }

    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colors.primary)
        .padding(7.dp)){
        Column {
            Text("Movies",color = Color.White)

        }
        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Box {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Dropdown Menu",
                    tint = Color.White,
                    modifier = Modifier
                        .clickable(onClick = {
                            menu = !menu
                        })
                )
                    DropdownMenu(
                        expanded = menu,
                        onDismissRequest = { menu = false },
                        modifier = Modifier
                            .background(color = MaterialTheme.colors.primary)
                            .padding(7.dp)
                    ) {
                        Row {
                            Text("Favorites ", color = Color.White)
                            Icon(
                                imageVector = (Icons.Default.Favorite),
                                contentDescription = "Favoriten"
                            )
                        }
                    }
            }
        }
    }
}

@Composable
fun MovieRow(movie: Movie){

    var image : ImageVector
    var expand by remember{
        mutableStateOf(false)
    }

    Card(shape = RoundedCornerShape(8.dp), modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)) {
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

@Composable
fun MovieList(movies : List<Movie>){
    LazyColumn{
        items(movies){ movie ->
            MovieRow(movie)
        }
    }
}
