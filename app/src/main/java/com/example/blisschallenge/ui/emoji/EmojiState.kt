package com.example.blisschallenge.ui.emoji

import com.example.blisschallenge.data.model.Emoji

data class EmojiState(
    val emojis: List<Emoji> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)