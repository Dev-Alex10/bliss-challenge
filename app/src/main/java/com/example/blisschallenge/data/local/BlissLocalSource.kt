package com.example.blisschallenge.data.local

import com.example.blisschallenge.data.domain.model.Avatar
import com.example.blisschallenge.data.domain.model.Emoji
import com.example.blisschallenge.data.local.avatar.AvatarDao
import com.example.blisschallenge.data.local.avatar.toDatabaseEntity
import com.example.blisschallenge.data.local.avatar.toDomain
import com.example.blisschallenge.data.local.emoji.EmojiDao
import com.example.blisschallenge.data.local.emoji.toDatabaseEntity
import com.example.blisschallenge.data.local.emoji.toDomain
import javax.inject.Inject

class BlissLocalSource @Inject constructor(
    private val emojiDao: EmojiDao,
    private val avatarDao: AvatarDao
) {
    fun getEmojis(): List<Emoji> {
        return emojiDao.getAllEmojis().map { it.toDomain() }
    }

    suspend fun setEmojis(emojis: List<Emoji>) {
        emojis.map { emojiDao.insertEmojis(it.toDatabaseEntity()) }
    }

    fun getAvatar(username: String): Avatar? {
        return avatarDao.getAvatar(username)?.toDomain()
    }
    fun getAvatars(): List<Avatar> {
        return avatarDao.getAvatars().map { it.toDomain() }
    }

    fun deleteAvatar(username: String) {
        avatarDao.deleteAvatar(username)
    }


    suspend fun insertAvatar(avatar: Avatar) {
        avatarDao.insertAvatar(avatar.toDatabaseEntity())
    }
}
