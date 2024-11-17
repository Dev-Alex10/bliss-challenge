package com.example.blisschallenge.data.remote

import com.example.blisschallenge.data.domain.model.Avatar
import com.example.blisschallenge.data.domain.model.Emoji
import javax.inject.Inject

class BlissRemoteDataSource @Inject constructor(
    private val gitHubAPI: GitHubAPI
) {
    suspend fun getEmojis() = gitHubAPI.getEmojis().map { Emoji(it.key, it.value) }
    suspend fun getAvatar(username: String): Avatar {
        try {
            return gitHubAPI.getAvatar(username)
        } catch (e: Exception) {
            println("getAvatar exception: ${e.localizedMessage}")
            if (e.localizedMessage?.contains("404") == true) {
                return Avatar(null, null, null, errorMessage = "User not found")
            }
            return Avatar(null, null, null, errorMessage = e.message)
        }
    }
}
