package com.example.todoappwithcleanarchitecture

import com.example.todoappwithcleanarchitecture.network.model.Post

data class PostState(
    val progressbar: Boolean = false,
    val post: List<Post> = emptyList(),
    val error: String? = null
)