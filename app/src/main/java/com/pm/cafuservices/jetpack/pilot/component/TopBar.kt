package com.pm.cafuservices.jetpack.pilot.component

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(text = "CA")
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Navigation back"
                )
            }
        },
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.primary
    )
}