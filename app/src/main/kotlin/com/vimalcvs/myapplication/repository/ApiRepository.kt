package com.vimalcvs.myapplication.repository


import com.vimalcvs.myapplication.data.ModelPost
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(private val client: HttpClient) {
    suspend fun getPosts(): List<ModelPost> {
        return client.get("/posts?_embed=comments").body()
    }
}
