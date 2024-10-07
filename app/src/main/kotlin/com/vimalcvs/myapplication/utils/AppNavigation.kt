package com.vimalcvs.myapplication.utils


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.vimalcvs.myapplication.data.ModelPost
import com.vimalcvs.myapplication.views.DetailScreen
import com.vimalcvs.myapplication.views.HomeScreen
import com.vimalcvs.myapplication.views.SplashScreen


@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "splash") {

        composable("splash") { SplashScreen(navController) }

        composable("home") { HomeScreen(navController) }


        composable("post/{post}") { backStackEntry ->
            val postJson = backStackEntry.arguments?.getString("post")
            val post = Gson().fromJson(postJson, ModelPost::class.java)
            DetailScreen(post, navController)
        }

    }
}
