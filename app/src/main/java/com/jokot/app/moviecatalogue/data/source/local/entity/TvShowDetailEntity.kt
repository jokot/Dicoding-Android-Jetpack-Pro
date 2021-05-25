package com.jokot.app.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowDetailEntity(
    val backdropPath: String?,
    val episodeRunTime: String,
    val firstAirDate: String,
    val genres: List<String>,
    val homepage: String,
    val id: Int,
    val inProduction: Boolean,
    val languages: List<String>,
    val lastAirDate: String,
    val name: String,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val seasons: List<SeasonEntity>,
    val status: String,
    val tagline: String,
    val type: String,
    val voteAverage: Int,
    val voteCount: Int
) : Parcelable
