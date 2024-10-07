package com.vimalcvs.myapplication.views

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.vimalcvs.myapplication.data.ModelPost
import com.vimalcvs.myapplication.utils.BottomNavigation
import com.vimalcvs.myapplication.utils.ErrorScreen
import com.vimalcvs.myapplication.utils.LoadingScreen
import com.vimalcvs.myapplication.viewmodel.PostViewModel
import com.vimalcvs.myapplication.viewmodel.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val context = LocalContext.current
    val viewModel: PostViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Home") },
                navigationIcon = {
                    IconButton(onClick = {
                        Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                    }
                }
            )
        },
        bottomBar = { BottomNavigation(navController) }
    ) { innerPadding ->
        PostListScreen(
            uiState = uiState,
            onRetryClick = { viewModel.getPosts() },
            navController = navController,
            innerPadding = innerPadding
        )
    }
}

@Composable
fun PostListScreen(
    uiState: UiState,
    onRetryClick: () -> Unit,
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        when (uiState) {
            is UiState.Loading -> LoadingScreen()
            is UiState.Error -> ErrorScreen(message = uiState.message, onRetryClick = onRetryClick)
            is UiState.SuccessPost -> PostList(posts = uiState.posts, navController = navController)
        }
    }
}

@Composable
fun PostList(posts: List<ModelPost>, navController: NavHostController) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(posts.size) { post ->
            ListPostItem(post = posts[post], navController = navController)
        }
    }
}

@Composable
fun ListPostItem(post: ModelPost, navController: NavHostController) {
    Card(
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp, pressedElevation = 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 80.dp)
            .padding(vertical = 2.dp)
            .clickable {
                val commentJson = Uri.encode(Gson().toJson(post))
                navController.navigate("post/$commentJson")
            },
    ) {
        Text(
            text = post.title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Start
        )
    }
}