package com.example.blisschallenge.data.local.emoji

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmojiDao {
    @Query("SELECT * FROM Emoji")
    fun getAllEmojis(): List<EmojiEntity>

    @Query("SELECT * FROM Emoji WHERE name= :emojiName")
    fun getEmoji(emojiName: String): EmojiEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmojis(emoji: EmojiEntity)
}
