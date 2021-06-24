package com.jokot.app.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_detail_entities")
data class MovieDetailEntity(
    @ColumnInfo(name = "adult")
    var adult: Boolean,

    @ColumnInfo(name = "backdropPath")
    var backdropPath: String?,

    @ColumnInfo(name = "budget")
    var budget: Int,

    @ColumnInfo(name = "genres")
    var genres: String,

    @ColumnInfo(name = "homepage")
    var homepage: String?,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieDetailId")
    var id: Int,

    @ColumnInfo(name = "imdbId")
    var imdbId: String?,

    @ColumnInfo(name = "originalLanguage")
    var originalLanguage: String,

    @ColumnInfo(name = "originalTitle")
    var originalTitle: String,

    @ColumnInfo(name = "overview")
    var overview: String?,

    @ColumnInfo(name = "popularity")
    var popularity: Double,

    @ColumnInfo(name = "posterPath")
    var posterPath: String?,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String,

    @ColumnInfo(name = "revenue")
    var revenue: Int,

    @ColumnInfo(name = "duration")
    var duration: String,

    @ColumnInfo(name = "status")
    var status: String,

    @ColumnInfo(name = "tagline")
    var tagline: String?,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "video")
    var video: Boolean,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: Int,

    @ColumnInfo(name = "voteCount")
    var voteCount: Int,

    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean = false
)
