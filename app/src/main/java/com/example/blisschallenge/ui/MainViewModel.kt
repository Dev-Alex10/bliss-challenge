package com.example.blisschallenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blisschallenge.data.DefaultBlissRepository
import com.example.blisschallenge.data.domain.model.Emoji
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: DefaultBlissRepository) :
    ViewModel() {
    private lateinit var emojis: List<Emoji>

    init {
        viewModelScope.launch(Dispatchers.IO) {
            emojis = repository.getLocalEmojis()
        }
    }

    private val _mainState: MutableStateFlow<MainViewState> = MutableStateFlow(
        MainViewState(
            text = "",
            emoji = null,
        )
    )
    val mainState: StateFlow<MainViewState>
        get() = _mainState.stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
            initialValue = _mainState.value
        )

    private suspend fun getEmojis(): List<Emoji> {
        return repository.getEmojis()
    }

    fun getRandomEmoji() {
        if (emojis.isNotEmpty()) {
            val randomIndex = emojis.indices.random()
            _mainState.update { it.copy(emoji = emojis[randomIndex]) }
        } else {
            viewModelScope.launch {
                getRemoteEmojis()
            }
        }
    }

    private suspend fun getRemoteEmojis() {
        emojis = getEmojis()
        repository.setEmojiList(emojis)
        _mainState.update { it.copy(emoji = emojis.random()) }
    }
}
