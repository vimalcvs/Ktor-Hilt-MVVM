package com.vimalcvs.myapplication.data

import kotlinx.serialization.Serializable


@Serializable
data class ModelPost(
    val body: String,
    val comments: List<ModelComment>,
    val id: Int,
    val title: String,
    val userId: Int
)

@Serializable
data class ModelComment(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)