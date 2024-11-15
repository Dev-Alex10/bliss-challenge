package com.example.blisschallenge.data.remote

import com.example.blisschallenge.data.remote.model.AvatarAPI
import com.example.blisschallenge.data.remote.model.RepoAPI
import retrofit2.http.GET

interface GitHubAPI {
    @GET("/emojis")
    suspend fun getEmojis(): Map<String, String>
    @GET("/users/{username}")
    suspend fun getAvatar(username: String): AvatarAPI
    @GET("/users/{username}/repos")
    suspend fun getRepos(username: String): List<RepoAPI>
}
