package com.vimalcvs.myapplication.utils

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigation(navController: NavController) {
    val context = LocalContext.current
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    NavigationBar(
        modifier = Modifier,
    ) {
        NavigationBarItem(
            icon = {
                Icon(Icons.Default.Home, contentDescription = "Home")
            },
            label = { Text(text = "Home") },
            selected = currentDestination?.route == "fragmentHome",
            onClick = { Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show() }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "Mail",
                )
            },
            label = { Text(text = "Category") },
            selected = currentDestination?.route == "fragmentCategory",
            onClick = { Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show() }
        )


        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "Settings",
                )
            },
            label = { Text(text = "Settings") },
            selected = currentDestination?.route == "fragmentSettings",
            onClick = { Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show() }
        )
    }
}
