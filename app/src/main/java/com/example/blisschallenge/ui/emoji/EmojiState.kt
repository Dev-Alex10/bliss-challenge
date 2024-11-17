package com.example.blisschallenge.ui.emoji

import com.example.blisschallenge.data.domain.model.Emoji

sealed interface EmojiScreenState {
    data object Loading : EmojiScreenState
    data class Success(
        val emojis: List<Emoji>,
        val refreshing: Boolean,
        val filterKeys: Set<String>
    ) : EmojiScreenState {
        val uiEmojis: List<Emoji> = emojis.filter { it.name !in filterKeys }
    }

    data class Error(val errorMessage: String) : EmojiScreenState
}
