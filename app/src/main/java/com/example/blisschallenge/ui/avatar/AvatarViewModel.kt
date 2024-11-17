package com.example.blisschallenge.ui.avatar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blisschallenge.data.DefaultBlissRepository
import com.example.blisschallenge.data.domain.model.Avatar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvatarViewModel @Inject constructor(
    private val avatarRepository: DefaultBlissRepository
) : ViewModel() {
    private val _avatarState: MutableStateFlow<AvatarViewState> = MutableStateFlow(
        AvatarViewState(
            avatars = emptyList(), errorMessage = ""
        )
    )
    val avatarState: MutableStateFlow<AvatarViewState>
        get() = _avatarState

    init {
        getAvatars()
    }

    private fun getAvatars() {
        viewModelScope.launch {
            val avatars = avatarRepository.getAvatars()
            println("Avatars found $avatars")
            println("updating state")
            _avatarState.update { it.copy(avatars = avatars) }
        }
    }

    fun removeAvatar(avatarToDelete: Avatar) {
        viewModelScope.launch {
            avatarRepository.deleteLocalAvatar(avatarToDelete.username)
            _avatarState.update {
                it.copy(avatars = _avatarState.value.avatars.filter { avatar -> avatar != avatarToDelete })
            }
        }
    }
}
