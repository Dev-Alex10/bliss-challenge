package com.example.blisschallenge.data.domain.emoji

import com.example.blisschallenge.data.remote.RemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmojiRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : EmojiRepository {
    override suspend fun getEmojis() = remoteDataSource.getEmojis()
}
