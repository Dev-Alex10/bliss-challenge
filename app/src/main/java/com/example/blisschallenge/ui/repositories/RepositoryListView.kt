package com.example.blisschallenge.ui.repositories

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun RepositoryListView(
    viewModel: RepositoryListViewModel = hiltViewModel(),
) {
    val repositories = viewModel.repositoryPager.collectAsLazyPagingItems()
    when (repositories.loadState.refresh) {
        is LoadState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(Modifier.size(64.dp))
            }
        }

        is LoadState.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error: Get repositories failed")
            }
        }

        is LoadState.NotLoading -> {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(
                    count = repositories.itemCount,
                ) { index ->
                    val repository = repositories[index]
                    Text(text = repository?.name ?: "Loading...")
                }
            }
        }
    }
}
