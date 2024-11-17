package com.example.blisschallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.blisschallenge.data.local.avatar.AvatarDao
import com.example.blisschallenge.data.local.avatar.AvatarEntity
import com.example.blisschallenge.data.local.emoji.EmojiDao
import com.example.blisschallenge.data.local.emoji.EmojiEntity

@Database(entities = [EmojiEntity::class, AvatarEntity::class], version = 1, exportSchema = false)
abstract class BlissDatabase : RoomDatabase() {
    abstract fun emojiDao(): EmojiDao
    abstract fun avatarDao(): AvatarDao
}
