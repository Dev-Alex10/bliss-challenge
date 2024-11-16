package com.example.blisschallenge.data.local.emoji

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.blisschallenge.data.domain.model.Emoji

@Entity(tableName = "Emoji")
data class EmojiEntity(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    @ColumnInfo(name = "url")
    val url: String,
)
fun EmojiEntity.toDomain(): Emoji {
    return Emoji(
        name = name,
        url = url
    )
}
fun Emoji.toDatabaseEntity(): EmojiEntity {
    return EmojiEntity(
        name = name,
        url = url
    )
}
