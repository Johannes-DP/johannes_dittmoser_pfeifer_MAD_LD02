package com.example.movieappmad23.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.models.MovieViewModel
import com.example.movieappmad23.models.getMovies
import com.example.movieappmad23.widgets.HorizontalScrollableImageView
import com.example.movieappmad23.widgets.MovieRow
import com.example.movieappmad23.widgets.SimpleTopAppBar

fun filterMovie(movieId: String): Movie {
    return getMovies().filter { it.id == movieId}[0]
}
@Composable
fun DetailScreen(
    navController: NavController,
    movieViewModel: MovieViewModel,
    movieId:String?){

    movieId?.let {
        val movie = movieViewModel.findMovie(movieId)

        // needed for show/hide snackbar
        val scaffoldState = rememberScaffoldState() // this contains the `SnackbarHostState`

        Scaffold(scaffoldState = scaffoldState, // attaching `scaffoldState` to the `Scaffold`
            topBar = {
                SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
                    Text(text = movie.title)
                }
            },
        ) { padding ->
            MainContent(Modifier.padding(padding), movieViewModel, movie, navController)
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier, movieViewModel: MovieViewModel, movie: Movie, navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            MovieRow(
                movie = movie,
                onItemClick = { movieId ->
                    navController.navigate(Screen.DetailScreen.withId(movieId))
                },
                onFavClick = {movieId -> movieViewModel.markFavorite(movieId)}
            )

            Spacer(modifier = Modifier.height(8.dp))

            Divider()

            Text(text = "Movie Images", style = MaterialTheme.typography.h5)

            HorizontalScrollableImageView(movie = movie)
        }
    }
}