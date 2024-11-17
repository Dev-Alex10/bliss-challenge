package com.example.blisschallenge.data

import com.example.blisschallenge.data.domain.model.Avatar
import com.example.blisschallenge.data.domain.model.Emoji
import com.example.blisschallenge.data.domain.model.Repo
import com.example.blisschallenge.data.local.BlissLocalSource
import com.example.blisschallenge.data.remote.BlissRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultBlissRepository @Inject constructor(
    private val remoteDataSource: BlissRemoteDataSource,
    private val localDataSource: BlissLocalSource
) : BlissRepository {
    fun getLocalEmojis(): List<Emoji> {
        return localDataSource.getEmojis()
    }

    override suspend fun getEmojis() = remoteDataSource.getEmojis()

    override suspend fun setEmojiList(emojis: List<Emoji>) {
        localDataSource.setEmojis(emojis)
    }

    override suspend fun getRepos(): List<Repo> {
        TODO("Not yet implemented")
    }

    override suspend fun getRemoteAvatar(username: String): Avatar {
        return remoteDataSource.getAvatar(username)
    }

    fun getLocalAvatar(username: String): Avatar? {
        return localDataSource.getAvatar(username)
    }
    fun getAvatars(): List<Avatar> {
        return localDataSource.getAvatars()
    }
    suspend fun insertLocalAvatar(avatar: Avatar) {
        localDataSource.insertAvatar(avatar)
    }
    fun deleteLocalAvatar(username: String) {
        localDataSource.deleteAvatar(username)
    }
}
