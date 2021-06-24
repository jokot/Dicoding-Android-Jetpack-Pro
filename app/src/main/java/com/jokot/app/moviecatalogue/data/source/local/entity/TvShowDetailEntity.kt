package com.jokot.app.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show_detail_entities")
data class TvShowDetailEntity(
    @ColumnInfo(name = "backdropPath")
    var backdropPath: String?,

    @ColumnInfo(name = "episodeRunTime")
    var episodeRunTime: String,

    @ColumnInfo(name = "firstAirDate")
    var firstAirDate: String,

    @ColumnInfo(name = "genres")
    var genres: String,

    @ColumnInfo(name = "homepage")
    var homepage: String,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvShowDetailId")
    var id: Int,

    @ColumnInfo(name = "inProduction")
    var inProduction: Boolean,

    @ColumnInfo(name = "lastAirDate")
    var lastAirDate: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "numberOfEpisodes")
    var numberOfEpisodes: Int,

    @ColumnInfo(name = "numberOfSeasons")
    var numberOfSeasons: Int,

    @ColumnInfo(name = "originalLanguage")
    var originalLanguage: String,

    @ColumnInfo(name = "originalName")
    var originalName: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "popularity")
    var popularity: Double,

    @ColumnInfo(name = "posterPath")
    var posterPath: String?,

    @ColumnInfo(name = "status")
    var status: String,

    @ColumnInfo(name = "tagline")
    var tagline: String,

    @ColumnInfo(name = "type")
    var type: String,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: Int,

    @ColumnInfo(name = "voteCount")
    var voteCount: Int,

    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean = false
)
