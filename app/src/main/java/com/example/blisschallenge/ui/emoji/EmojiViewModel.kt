package com.example.blisschallenge.ui.emoji

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blisschallenge.data.domain.emoji.EmojiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmojiViewModel @Inject constructor(
    private val emojiRepository: EmojiRepository
) : ViewModel() {
    val emojiState: MutableStateFlow<EmojiState> =
        MutableStateFlow(
            EmojiState(
                emojis = emptyList(),
                error = ""
            )
        )
    init {
        prepareEmojiList()
    }

    private fun prepareEmojiList() {
        print("prepareEmojiList")
        viewModelScope.launch {
            try {
                val emojis = emojiRepository.getEmojis()
                println("emojis: $emojis")
                emojiState.update { it.copy(emojis = emojis) }
            } catch (e: Exception) {
                emojiState.update { it.copy(error = e.message ?: "An error occurred") }
            }
        }
    }
    fun getRandomEmoji(): String {
        val emojis = emojiState.value.emojis
        return emojis.random().url
    }
}
