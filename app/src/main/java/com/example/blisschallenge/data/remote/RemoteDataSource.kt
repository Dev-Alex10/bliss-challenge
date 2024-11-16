package com.example.blisschallenge.data.remote

import com.example.blisschallenge.data.model.Emoji
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val gitHubAPI: GitHubAPI
) {
    suspend fun getEmojis() = gitHubAPI.getEmojis().map { Emoji(it.key, it.value) }
}
