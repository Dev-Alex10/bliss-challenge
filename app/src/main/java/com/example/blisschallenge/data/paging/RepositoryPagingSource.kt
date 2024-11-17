package com.example.blisschallenge.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.blisschallenge.data.DefaultBlissRepository
import com.example.blisschallenge.data.domain.model.Repository

class RepositoryPagingSource(
    private val page: Int, private val repository: DefaultBlissRepository
) : PagingSource<Int, Repository>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        val nextPage = params.key ?: page
        val result = repository.getRepositories(nextPage)
        result.fold(onSuccess = { repositories ->
            return LoadResult.Page(
                data = repositories,
                prevKey = if (nextPage == page) null else nextPage - 1,
                nextKey = if (repositories.isEmpty()) null else nextPage + 1
            )
        }, onFailure = { error ->
            return LoadResult.Error(error)
        })
    }

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return state.anchorPosition
    }
}
