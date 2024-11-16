package com.example.blisschallenge.data.remote

import com.example.blisschallenge.data.domain.model.Emoji
import javax.inject.Inject

class BlissRemoteDataSource @Inject constructor(
    private val gitHubAPI: GitHubAPI
) {
    suspend fun getEmojis() = gitHubAPI.getEmojis().map { Emoji(it.key, it.value) }
}
