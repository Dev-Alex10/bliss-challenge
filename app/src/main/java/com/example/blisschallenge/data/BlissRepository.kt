package com.example.blisschallenge.data

import com.example.blisschallenge.data.domain.model.Avatar
import com.example.blisschallenge.data.domain.model.Emoji
import com.example.blisschallenge.data.domain.model.Repository

interface BlissRepository {
    suspend fun getRepositories(page: Int): Result<List<Repository>>
    suspend fun getAvatar(username:String): Result<Avatar>
    suspend fun getEmojis(): Result<List<Emoji>>
    suspend fun setEmojiList(emojis: List<Emoji>)
}
