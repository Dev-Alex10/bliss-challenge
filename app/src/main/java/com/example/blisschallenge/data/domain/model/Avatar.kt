package com.example.blisschallenge.data.domain.model

import com.google.gson.annotations.SerializedName

data class Avatar(
    @SerializedName("login")
    val username: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)
