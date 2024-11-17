package com.example.blisschallenge.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun MainView(
    viewModel: MainViewModel = hiltViewModel(),
    onClickEmojiList: () -> Unit,
    onClickAvatarList: () -> Unit,
    onClickRepositoryList: () -> Unit,
    modifier: Modifier,
) {
    val state = viewModel.mainState.collectAsState()
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (state.value.image != null) {
            AsyncImage(
                model = state.value.image?.url,
                contentDescription = state.value.image?.description ?: "",
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
        Row {
            OutlinedTextField(
                value = state.value.text,
                onValueChange = { viewModel.onTextChanged(it) },
                label = { Text(text = "Search Avatar") },
                modifier = modifier,
                singleLine = true,
                supportingText = if (state.value.errorMessage.isNotEmpty()) {
                    { Text(text = state.value.errorMessage, color = Color.Red) }
                } else null,
                trailingIcon = {
                    IconButton({ viewModel.getAvatar(state.value.text) }) {
                        Icon(Icons.Outlined.Search, contentDescription = "Search")
                    }
                }
            )
        }
        Button(
            onClick = onClickAvatarList,
            modifier = modifier,
        ) {
            Text(text = "Show Avatar List")
        }
        Button(
            onClick = onClickRepositoryList,
            modifier = modifier,
        ) {
            Text(text = "Show Repository List")
        }
    }
}
