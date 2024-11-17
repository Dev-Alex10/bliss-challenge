package com.example.blisschallenge.data

import com.example.blisschallenge.data.domain.model.Avatar
import com.example.blisschallenge.data.domain.model.Emoji
import com.example.blisschallenge.data.domain.model.Repo

interface BlissRepository {
    suspend fun getRepos(): List<Repo>
    suspend fun getRemoteAvatar(username:String): Avatar?
    suspend fun getEmojis(): List<Emoji>
    suspend fun setEmojiList(emojis: List<Emoji>)
}
