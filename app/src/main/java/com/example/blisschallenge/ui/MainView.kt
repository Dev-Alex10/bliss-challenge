package com.example.blisschallenge.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun MainView(
    viewModel: MainViewModel = hiltViewModel(),
    onClickEmojiList: () -> Unit,
    modifier: Modifier,
) {
    val state = viewModel.mainState.collectAsState()
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (state.value.emoji != null) {
            AsyncImage(
                model = state.value.emoji?.url,
                contentDescription = state.value.emoji?.name ?: "",
                modifier = modifier.size(128.dp),
            )
        }
        Button(
            onClick = { viewModel.getRandomEmoji() },//showRandomEmoji,
            modifier = modifier,
        ) {
            Text(text = "Show Random Emoji")
        }
        Button(
            onClick = onClickEmojiList,
            modifier = modifier,
        ) {
            Text(text = "Show Emoji List")
        }
    }
}
