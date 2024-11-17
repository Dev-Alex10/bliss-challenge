package com.example.blisschallenge.data.local.avatar

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AvatarDao {
    @Query("SELECT * FROM Avatar")
    fun getAvatars(): List<AvatarEntity>

    @Query("SELECT * FROM Avatar WHERE username= :username")
    fun getAvatar(username: String): AvatarEntity?

    @Query("DELETE FROM Avatar WHERE username= :username")
    fun deleteAvatar(username: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAvatar(avatar: AvatarEntity)
}
