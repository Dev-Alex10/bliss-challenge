package com.example.blisschallenge.ui.emoji

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blisschallenge.data.DefaultBlissRepository
import com.example.blisschallenge.data.domain.model.Emoji
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EmojiViewModel @Inject constructor(
    private val emojiRepository: DefaultBlissRepository
) : ViewModel() {
    private var emojiJob: Job? = null
    private var response: List<Emoji> = emptyList()
    private val _emojiState: MutableStateFlow<EmojiState> =
        MutableStateFlow(
            EmojiState(
                emojis = emptyList(),
                errorMessage = ""
            )
        )
    val emojiState: StateFlow<EmojiState> get() = _emojiState

    init {
        prepareEmojiList()
    }

    private fun prepareEmojiList() {
        emojiJob?.cancel()
        emojiJob = viewModelScope.launch {
            try {
                val emojis = requestEmojis()
                println("emojis: $emojis")
                _emojiState.update { it.copy(emojis = emojis) }
            } catch (e: Exception) {
                _emojiState.update { it.copy(errorMessage = e.message ?: "An error occurred") }
            }
        }
    }

    private suspend fun requestEmojis(): List<Emoji> {
        withContext(Dispatchers.IO) {
            response = emojiRepository.getLocalEmojis()
        }
        if (response.isEmpty()) {
            println("requestEmojis (response is empty)")
            response = emojiRepository.getEmojis()
            populateDatabase(response)
        }
        return response.map { it }
    }

    private suspend fun populateDatabase(apiResponse: List<Emoji>) {
        withContext(Dispatchers.IO) {
            emojiRepository.setEmojiList(apiResponse)
        }
    }

    fun removeEmojiFromState(emoji: Emoji) {
        val emojis = _emojiState.value.emojis.toMutableList().apply { remove(emoji) }
        _emojiState.update { it.copy(emojis = emojis) }
    }

    fun resetState() {
        viewModelScope.launch {
            _emojiState.update { it.copy(isLoading = true) }
            delay(500)
            _emojiState.update { it.copy(emojis = response, errorMessage = "", isLoading = false) }
        }
    }
}
