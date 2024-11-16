package com.example.blisschallenge.ui

import com.example.blisschallenge.data.domain.model.Emoji

data class MainViewState(
    val text : String = "",
    val emoji: Emoji? = null
)
