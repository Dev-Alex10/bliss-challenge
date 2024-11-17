package com.example.blisschallenge.data.remote

import com.example.blisschallenge.data.domain.model.Avatar
import com.example.blisschallenge.data.domain.model.Repository
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubAPI {
    @GET("/emojis")
    suspend fun getEmojis(): Map<String, String>

    @GET("/users/{username}")
    suspend fun getAvatar(@Path("username") username: String): Avatar

    @GET("/users/google/repos")
    suspend fun getRepositories(@Query("page") page: Int): List<Repository>
}
