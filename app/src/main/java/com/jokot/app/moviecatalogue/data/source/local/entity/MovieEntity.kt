package com.jokot.app.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_entities")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String,

    @ColumnInfo(name = "score")
    var score: Int,

    @ColumnInfo(name = "posterPath")
    var posterPath: String?,

    @ColumnInfo(name = "bannerPath")
    var bannerPath: String?,

    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean = false,

    @ColumnInfo(name = "isNowPlaying")
    var isNowPlaying: Boolean = false,

    @ColumnInfo(name = "isPopular")
    var isPopular: Boolean = false,

    @ColumnInfo(name = "isTopRated")
    var isTopRated: Boolean = false,

    @ColumnInfo(name = "isUpcoming")
    var isUpcoming: Boolean = false,
) {
    @Embedded
    var movieDetailEntity: MovieDetailEntity? = null
}
