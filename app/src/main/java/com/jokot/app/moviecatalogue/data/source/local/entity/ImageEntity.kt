package com.jokot.app.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_entities")
data class ImageEntity(
    @ColumnInfo(name = "backdropSize")
    val backdropSize: String,

    @ColumnInfo(name = "posterSize")
    val posterSize: String,

    @ColumnInfo(name = "secureBaseUrl")
    val secureBaseUrl: String,

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "imageId")
    val id: Int = 0
)
