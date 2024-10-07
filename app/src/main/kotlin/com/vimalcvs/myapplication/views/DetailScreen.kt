package com.vimalcvs.myapplication.views

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vimalcvs.myapplication.data.ModelComment
import com.vimalcvs.myapplication.data.ModelPost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(post: ModelPost, navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = post.userId.toString()) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            DetailScreenView(
                post = post,
                innerPadding = innerPadding,
                navController = navController
            )
        }
    )
}

@Composable
fun DetailScreenView(
    post: ModelPost,
    innerPadding: PaddingValues,
    navController: NavHostController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = post.body,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
        items(post.comments.size) { index ->
            ListPostItem(
                post = post.comments[index],
                navController = navController,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }

    }
}
@Composable
fun ListPostItem(
    post: ModelComment,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = navController.context
    Card(
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp, pressedElevation = 1.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 80.dp)
            .padding(vertical = 2.dp)
            .clickable {
                Toast
                    .makeText(
                        context,
                        "Coming Soon",
                        Toast.LENGTH_SHORT
                    )
                    .show()
            },

        ) {
        Text(
            text = post.name,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Start
        )
    }
}

