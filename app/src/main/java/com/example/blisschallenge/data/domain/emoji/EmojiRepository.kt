package com.example.blisschallenge.data.domain.emoji

import com.example.blisschallenge.data.model.Emoji

interface EmojiRepository {
    suspend fun getEmojis(): List<Emoji>
}
