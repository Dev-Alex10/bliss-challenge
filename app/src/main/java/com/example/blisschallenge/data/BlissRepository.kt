package com.example.blisschallenge.data

import com.example.blisschallenge.data.domain.model.Avatar
import com.example.blisschallenge.data.domain.model.Emoji
import com.example.blisschallenge.data.domain.model.Repository

interface BlissRepository {
    suspend fun getRepos(): List<Repository>
    suspend fun getRemoteAvatar(username:String): Result<Avatar>
    suspend fun getEmojis(): Result<List<Emoji>>
    suspend fun setEmojiList(emojis: List<Emoji>)
}
