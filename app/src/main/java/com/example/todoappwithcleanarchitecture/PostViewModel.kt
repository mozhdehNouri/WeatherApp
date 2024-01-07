package com.example.todoappwithcleanarchitecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class PostViewModel : ViewModel(),
    ContainerHost<PostState, ErrorShowingType> {

    private val getPosts = GetPosts(PostApi.providePostApi())
    override val container: Container<PostState, ErrorShowingType>
        get() = container(PostState())

    init {
        getPost()
    }

    private val posts = getPosts.invoke()
    private fun getPost() {
        intent {
            posts.onEach { dataState ->
                when (dataState) {
                    is DataState.Loading -> {
                        reduce {
                            state.copy(progressbar = dataState.loading)
                        }
                    }

                    is DataState.Success -> {
                        reduce {
                            state.copy(post = dataState.data)
                        }
                    }

                    is DataState.Error -> {
                        when (dataState.errorShowingType) {
                            is ErrorShowingType.Toast -> {
                                postSideEffect(
                                    ErrorShowingType.Toast(
                                        dataState.errorShowingType.message
                                    )
                                )
                            }

                            is ErrorShowingType.SnackBar -> {
                                postSideEffect(
                                    ErrorShowingType.SnackBar(
                                        dataState.errorShowingType.message
                                    )
                                )
                            }
                        }
                    }
                }
            }.launchIn(viewModelScope)

        }

    }

}