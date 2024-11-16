package com.example.blisschallenge.di

import android.content.Context
import androidx.room.Room
import com.example.blisschallenge.data.local.BlissDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideEmojiDao(database: BlissDatabase) = database.emojiDao()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): BlissDatabase {
        return Room.databaseBuilder(
            appContext,
            BlissDatabase::class.java,
            "bliss_database"
        ).build()
    }
}
