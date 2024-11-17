package com.example.blisschallenge.data.domain.model

import com.google.gson.annotations.SerializedName

data class Repository(
    val id: Long,
    @SerializedName("full_name")
    val name: String
)
