package com.example.blisschallenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blisschallenge.data.DefaultBlissRepository
import com.example.blisschallenge.data.domain.model.Avatar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: DefaultBlissRepository) :
    ViewModel() {

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

    fun getRandomEmoji() {
        viewModelScope.launch {
            repository.getEmojis().fold(
                onSuccess = { emojis ->
                    val randomEmoji = emojis.random()
                    _mainState.update {
                        it.copy(
                            image = randomEmoji.toMainViewImage()
                        )
                    }
                },
                onFailure = {
                    _mainState.update {
                        it.copy(
                            errorMessage = it.errorMessage
                        )
                    }
                }
            )
        }
    }

    fun getAvatar(username: String) {
        viewModelScope.launch {
            val remoteAvatar = repository.getAvatar(username)
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

    private fun updateState(avatar: Avatar?, errorMessage: String = "") {
        _mainState.update {
            it.copy(
                image = avatar?.toMainViewImage(), errorMessage = errorMessage
            )
        }
    }
}
