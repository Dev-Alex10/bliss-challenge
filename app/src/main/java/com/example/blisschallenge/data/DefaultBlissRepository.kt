package com.example.blisschallenge.data

import com.example.blisschallenge.data.domain.model.Avatar
import com.example.blisschallenge.data.domain.model.Emoji
import com.example.blisschallenge.data.domain.model.Repository
import com.example.blisschallenge.data.local.BlissLocalSource
import com.example.blisschallenge.data.remote.BlissRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultBlissRepository @Inject constructor(
    private val remoteDataSource: BlissRemoteDataSource,
    private val localDataSource: BlissLocalSource
) : BlissRepository {

    override suspend fun getEmojis(): Result<List<Emoji>> {
        val emojis = localDataSource.getEmojis()
        if (emojis.isNotEmpty()) {
            return Result.success(emojis)
        }
        return remoteDataSource.getEmojis().onSuccess {
            localDataSource.setEmojis(emojis)
        }
    }

    override suspend fun setEmojiList(emojis: List<Emoji>) {
        localDataSource.setEmojis(emojis)
    }

    override suspend fun getRepos(): List<Repository> {
        TODO("Not yet implemented")
    }

    override suspend fun getRemoteAvatar(username: String): Result<Avatar> {
        return remoteDataSource.getAvatar(username).onSuccess { avatar ->
            localDataSource.insertAvatar(avatar)
        }
    }

   suspend fun getLocalAvatar(username: String): Avatar? {
        return localDataSource.getAvatar(username)
    }

   suspend fun getAvatars(): List<Avatar> {
        return localDataSource.getAvatars()
    }

   suspend fun deleteLocalAvatar(username: String) {
        localDataSource.deleteAvatar(username)
    }
}
