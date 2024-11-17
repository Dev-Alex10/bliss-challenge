package com.example.blisschallenge.ui

import com.example.blisschallenge.data.domain.model.Avatar
import com.example.blisschallenge.data.domain.model.Emoji

data class MainViewState(
    val text: String = "",
    val image: MainViewImage? = null,
    val errorMessage: String = ""
)

data class MainViewImage(
    val description: String,
    val url: String
)

fun Emoji.toMainViewImage() = MainViewImage(
    description = this.name,
    url = this.url
)

fun Avatar.toMainViewImage() = MainViewImage(
    description = this.username ?: "",
    url = this.avatarUrl ?: ""
)
