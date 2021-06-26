package com.jokot.app.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show_entities")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvShowId")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "releaseData")
    var releaseDate: String,

    @ColumnInfo(name = "score")
    var score: Int,

    @ColumnInfo(name = "posterPath")
    var posterPath: String?,

    @ColumnInfo(name = "bannerPath")
    var bannerPath: String?,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false,

    @ColumnInfo(name = "isOnAir")
    var isOnAir: Boolean = false,

    @ColumnInfo(name = "isPopular")
    var isPopular: Boolean = false,

    @ColumnInfo(name = "isTopRated")
    var isTopRated: Boolean = false,

    @ColumnInfo(name = "isAiringToday")
    var isAiringToday: Boolean = false,
) {
    @Embedded
    var tvShowDetailEntity: TvShowDetailEntity? = null
}
