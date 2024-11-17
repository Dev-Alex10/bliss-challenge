package com.example.blisschallenge.data.local.avatar

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.blisschallenge.data.domain.model.Avatar

@Entity(tableName = "Avatar")
data class AvatarEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "login")
    val login: String,
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String
)

fun AvatarEntity.toDomain(): Avatar {
    return Avatar(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
    )
}

fun Avatar.toDatabaseEntity(): AvatarEntity {
    if (login.isNullOrEmpty() || avatarUrl.isNullOrEmpty()) {
        throw IllegalArgumentException("login or avatarUrl cannot be empty")
    }
    return AvatarEntity(
        login = login,
        avatarUrl = avatarUrl
    )
}
