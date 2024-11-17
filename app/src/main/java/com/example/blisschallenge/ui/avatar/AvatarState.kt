package com.example.blisschallenge.ui.avatar

import com.example.blisschallenge.data.domain.model.Avatar

data class AvatarViewState(
    val avatars: List<Avatar> = emptyList(),
    val errorMessage: String = ""
)
