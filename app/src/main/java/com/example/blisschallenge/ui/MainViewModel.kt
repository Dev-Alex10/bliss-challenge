package com.example.blisschallenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blisschallenge.data.DefaultBlissRepository
import com.example.blisschallenge.data.domain.model.Avatar
import com.example.blisschallenge.data.domain.model.Emoji
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: DefaultBlissRepository) :
    ViewModel() {
    private lateinit var emojis: List<Emoji>

    init {
        viewModelScope.launch(Dispatchers.IO) {
            emojis = repository.getEmojis().getOrNull().orEmpty()
        }
    }

    private val _mainState: MutableStateFlow<MainViewState> = MutableStateFlow(
        MainViewState(
            text = "",
            image = null,
        )
    )
    val mainState: StateFlow<MainViewState>
        get() = _mainState.stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
            initialValue = _mainState.value
        )

    fun onTextChanged(text: String) {
        _mainState.update { it.copy(text = text) }
    }

    private suspend fun getEmojis(): List<Emoji> {
        return repository.getEmojis().getOrNull().orEmpty()
    }

    fun getRandomEmoji() {
        if (emojis.isNotEmpty()) {
            val randomIndex = emojis.indices.random()
            _mainState.update { it.copy(image = emojis[randomIndex].toMainViewImage()) }
            return
        }
        viewModelScope.launch {
            getRemoteEmojis()
        }
    }

    private suspend fun getRemoteEmojis() {
        emojis = getEmojis()
        repository.setEmojiList(emojis)
        _mainState.update { it.copy(image = emojis.random().toMainViewImage()) }
    }

    fun getAvatar(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val avatar = repository.getLocalAvatar(username)
            if (avatar != null) {
                println("Avatar found locally")
                updateState(avatar)
                return@launch
            }
            val remoteAvatar = repository.getRemoteAvatar(username)
            remoteAvatar.fold(
                onSuccess = {
                    println("Avatar found remotely")
                    updateState(it)
                },
                onFailure = {
                    updateState(null, it.message ?: "Unknown error")
                }
            )
        }
    }
    private suspend fun updateState(avatar: Avatar?, errorMessage: String = "") {
        withContext(Dispatchers.Main) {
            _mainState.update {
                it.copy(
                    image = avatar?.toMainViewImage(), errorMessage = errorMessage
                )
            }
        }
    }
}
