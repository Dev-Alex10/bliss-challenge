package com.example.blisschallenge.data.local.avatar

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AvatarDao {
    @Query("SELECT * FROM Avatar")
    suspend fun getAvatars(): List<AvatarEntity>

    @Query("SELECT * FROM Avatar WHERE username= :username")
    suspend fun getAvatar(username: String): AvatarEntity?

    @Query("DELETE FROM Avatar WHERE username= :username")
    suspend fun deleteAvatar(username: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAvatar(avatar: AvatarEntity)
}
