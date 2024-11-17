package com.example.blisschallenge.data.local.emoji

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmojiDao {
    @Query("SELECT * FROM Emoji")
    fun getAllEmojis(): List<EmojiEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmojis(emoji: EmojiEntity)
}
