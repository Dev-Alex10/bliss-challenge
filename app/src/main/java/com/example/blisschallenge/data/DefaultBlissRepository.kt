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

    override suspend fun getRepos(): List<Repo> {
        TODO("Not yet implemented")
    }

    override suspend fun getAvatars(): List<Avatar> {
        TODO("Not yet implemented")
    }

    override suspend fun getEmojis() = remoteDataSource.getEmojis()
    override suspend fun setEmojiList(emojis: List<Emoji>) {
        localDataSource.setEmojis(emojis)
    }
}
