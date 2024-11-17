package com.example.blisschallenge.data.local.avatar

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AvatarDao {
    @Query("SELECT * FROM Avatar")
    fun getAvatars(): List<AvatarEntity>

    @Query("SELECT * FROM Avatar WHERE login= :login")
    fun getAvatar(login: String): AvatarEntity?

    @Query("DELETE FROM Avatar WHERE login= :login")
    fun deleteAvatar(login: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAvatar(avatar: AvatarEntity)
}
