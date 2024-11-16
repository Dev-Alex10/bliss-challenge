package com.example.blisschallenge.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MainView(
    onClickEmojiList: () -> Unit,
    modifier: Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            onClick = onClickEmojiList,
            modifier = modifier,
        ) {
            Text(text = "Show Emoji List")
        }
        Button(
            onClick = {},//showRandomEmoji,
            modifier = modifier,
        ) {
            Text(text = "Show Random Emoji")
        }
    }
}
