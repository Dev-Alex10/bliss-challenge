package com.example.blisschallenge.data.remote

import com.example.blisschallenge.data.domain.model.Emoji
import javax.inject.Inject

class BlissRemoteDataSource @Inject constructor(
    private val gitHubAPI: GitHubAPI
) {
    suspend fun getEmojis() = kotlin.runCatching {
        gitHubAPI.getEmojis().map { Emoji(it.key, it.value) }
    }

    suspend fun getAvatar(username: String) = kotlin.runCatching {
        gitHubAPI.getAvatar(username)
    }

    suspend fun getRepositories(page: Int) = kotlin.runCatching {
        gitHubAPI.getRepositories(page)
    }
}
