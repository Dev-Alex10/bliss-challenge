package com.example.blisschallenge.ui.emoji

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun EmojiView(
    viewModel: EmojiViewModel = hiltViewModel(),
    modifier: Modifier,
) {
    val state = viewModel.emojiState.collectAsState()
    LazyVerticalGrid(
        columns = GridCells.FixedSize(size = 128.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(state.value.emojis) { emoji ->
            AsyncImage(
                model = emoji.url,
                contentDescription = emoji.name,
                modifier = modifier,
                onLoading = {
                    // Display a loading spinner
                    Icons.Outlined.Refresh
                },
                onError = {
                    // Display an error icon
                    Icons.Outlined.Close
                },
            )
        }
    }
}
