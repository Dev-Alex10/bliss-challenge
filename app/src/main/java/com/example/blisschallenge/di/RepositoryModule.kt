package com.example.blisschallenge.di

import com.example.blisschallenge.data.DefaultBlissRepository
import com.example.blisschallenge.data.local.BlissLocalSource
import com.example.blisschallenge.data.remote.BlissRemoteDataSource
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
        remoteDataSource: BlissRemoteDataSource,
        localDataSource: BlissLocalSource
    ): DefaultBlissRepository {
        return DefaultBlissRepository(remoteDataSource, localDataSource)
    }
}
