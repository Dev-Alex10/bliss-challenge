package com.example.blisschallenge.ui.emoji

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blisschallenge.data.DefaultBlissRepository
import com.example.blisschallenge.data.domain.model.Emoji
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmojiViewModel @Inject constructor(
    private val emojiRepository: DefaultBlissRepository
) : ViewModel() {
    private val _emojiState: MutableStateFlow<EmojiScreenState> =
        MutableStateFlow(EmojiScreenState.Loading)
    val emojiState: StateFlow<EmojiScreenState> get() = _emojiState

    init {
        prepareEmojiList()
    }

    private fun prepareEmojiList() {
        viewModelScope.launch {
            val emojis = emojiRepository.getEmojis()
            println("emojis: $emojis")
            emojis.fold(
                onSuccess = { emojiList ->
                    _emojiState.update {
                        EmojiScreenState.Success(
                            emojis = emojiList,
                            refreshing = false,
                            filterKeys = emptySet()
                        )
                    }
                },
                onFailure = {
                    _emojiState.update { EmojiScreenState.Error(errorMessage = "An error occurred") }
                }
            )
        }
    }

    fun removeEmojiFromState(emoji: Emoji) {
        _emojiState.update { state ->
            when (state) {
                is EmojiScreenState.Success -> {
                    state.copy(filterKeys = state.filterKeys + emoji.name)
                }

                else -> state
            }
        }
    }

    fun resetState() {
        viewModelScope.launch {
            _emojiState.update {
                when (val state = it) {
                    is EmojiScreenState.Success -> state.copy(refreshing = true)
                    else -> state
                }
            }
            delay(500)
            _emojiState.update {
                when (val state = it) {
                    is EmojiScreenState.Success -> state.copy(
                        refreshing = false,
                        filterKeys = emptySet()
                    )

                    else -> state
                }
            }
        }
    }
}
