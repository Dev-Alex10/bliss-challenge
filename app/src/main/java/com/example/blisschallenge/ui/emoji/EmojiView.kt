package com.example.blisschallenge.ui.emoji

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.blisschallenge.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmojiView(
    viewModel: EmojiViewModel = hiltViewModel(),
    modifier: Modifier,
) {
    val context = LocalContext.current
    val state = viewModel.emojiState.collectAsState()
    PullToRefreshBox(
        onRefresh = { viewModel.resetState() },
        isRefreshing = state.value.isLoading
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center
        ) {
            items(state.value.emojis) { emoji ->
                AsyncImage(
                    model = ImageRequest.Builder(context = context)
                        .data(emoji.url)
                        .crossfade(true)
                        .build(),
                    contentDescription = emoji.name,
                    modifier = modifier
                        .size(84.dp)
                        .clickable { viewModel.removeEmojiFromState(emoji) },
                    error = painterResource(R.drawable.error),
                    onLoading = {
                        Icons.Outlined.Refresh
                    },
                )
            }
        }
    }

}
