package com.example.blisschallenge.data.local

import com.example.blisschallenge.data.domain.model.Emoji
import com.example.blisschallenge.data.local.emoji.EmojiDao
import com.example.blisschallenge.data.local.emoji.toDatabaseEntity
import com.example.blisschallenge.data.local.emoji.toDomain
import javax.inject.Inject

class BlissLocalSource @Inject constructor(private val emojiDao: EmojiDao) {
    fun getEmojis(): List<Emoji> {
        return emojiDao.getAllEmojis().map { it.toDomain() }
    }

    fun getEmoji(emojiName: String) {
        emojiDao.getEmoji(emojiName = emojiName).toDomain()
    }

    suspend fun setEmojis(emojis: List<Emoji>) {
        emojis.map { emojiDao.insertEmojis(it.toDatabaseEntity()) }
    }
}
