package com.example.blisschallenge.ui.avatar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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

@Composable
fun AvatarView(
    viewModel: AvatarViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val state = viewModel.avatarState.collectAsState()
    val context = LocalContext.current
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center
    ) {
        items(state.value.avatars) { avatar ->
            AsyncImage(
                model = ImageRequest.Builder(context = context)
                    .data(avatar.avatarUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = avatar.username,
                modifier = modifier
                    .size(84.dp)
                    .clickable { viewModel.removeAvatar(avatar) },
                error = painterResource(R.drawable.error),
            )
        }
    }
}
