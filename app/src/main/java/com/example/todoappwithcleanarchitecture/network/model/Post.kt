package com.example.todoappwithcleanarchitecture.network.model

import kotlinx.serialization.Serializable


@Serializable
data class Post(
    val userIs: Int,
    val id: Int,
    val title: String,
    val body: String
)
