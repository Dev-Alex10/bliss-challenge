package com.example.blisschallenge.data.remote

import com.example.blisschallenge.data.domain.model.Avatar
import com.example.blisschallenge.data.domain.model.Repo
import retrofit2.http.GET

interface GitHubAPI {
    @GET("/emojis")
    suspend fun getEmojis(): Map<String, String>

    @GET("/users/{username}")
    suspend fun getAvatar(username: String): Avatar

    @GET("/users/{username}/repos")
    suspend fun getRepos(username: String): List<Repo>
}
