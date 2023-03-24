package com.example.movieapp.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TopAppBar(navController: NavController) {
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
                    Row (Modifier.clickable(onClick = {navController.navigate("fav")})){
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
fun TopAppBar(navController: NavController, title: String){
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colors.primary)
        .padding(7.dp)) {
        Column{
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back", tint = Color.White, modifier = Modifier
                .clickable(onClick = {navController.popBackStack()})
            )
        }
        Column{
            Text(title,color = Color.White)
        }

    }
}