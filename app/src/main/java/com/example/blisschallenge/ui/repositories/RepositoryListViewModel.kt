package com.example.blisschallenge.ui.repositories

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.blisschallenge.data.DefaultBlissRepository
import com.example.blisschallenge.data.paging.RepositoryPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepositoryListViewModel @Inject constructor(
    private val repository: DefaultBlissRepository
) : ViewModel() {
    val repositoryPager = Pager(PagingConfig(pageSize = 30)) {
        RepositoryPagingSource(1, repository)
    }.flow
}
