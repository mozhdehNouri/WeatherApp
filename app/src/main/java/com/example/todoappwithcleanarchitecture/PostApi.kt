package com.example.todoappwithcleanarchitecture

import com.example.todoappwithcleanarchitecture.network.model.Post
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation

interface PostApi {

    suspend fun getPosts(): List<Post>

    companion object {
        val httpClient = HttpClient(Android) {
            install(ContentNegotiation) {

            }
        }

        fun providePostApi(): PostApi = PostApiImpl(httpClient)
    }
}