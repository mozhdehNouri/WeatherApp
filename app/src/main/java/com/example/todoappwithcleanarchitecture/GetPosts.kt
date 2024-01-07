package com.example.todoappwithcleanarchitecture

import com.example.todoappwithcleanarchitecture.network.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPosts(
    private val postApi: PostApi
) {

    fun invoke(): Flow<DataState<List<Post>>> {
        return flow {
            emit(DataState.Loading(true))
            try {
                val post = postApi.getPosts()
                emit(DataState.Success(data = post))
            } catch (e: Exception) {
                emit(
                    DataState.Error(
                        ErrorShowingType.Toast(
                            e.message ?: "something is wrong"
                        )
                    )
                )
            } finally {
                emit(DataState.Loading(false))
            }


        }

    }

}