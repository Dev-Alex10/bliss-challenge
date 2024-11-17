package com.example.blisschallenge.data

import com.example.blisschallenge.data.domain.model.Avatar
import com.example.blisschallenge.data.domain.model.Emoji
import com.example.blisschallenge.data.domain.model.Repository

interface BlissRepository {
    suspend fun getRepositories(page: Int): Result<List<Repository>>
    suspend fun getRemoteAvatar(username: String): Avatar?
    suspend fun getEmojis(): List<Emoji>
    suspend fun setEmojiList(emojis: List<Emoji>)
}
