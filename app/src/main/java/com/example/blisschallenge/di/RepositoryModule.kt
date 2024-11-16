package com.example.blisschallenge.di

import com.example.blisschallenge.data.domain.emoji.EmojiRepository
import com.example.blisschallenge.data.domain.emoji.EmojiRepositoryImpl
import com.example.blisschallenge.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideEmojiRepository(
        remoteDataSource: RemoteDataSource
    ): EmojiRepository {
        return EmojiRepositoryImpl(remoteDataSource)
    }
}
