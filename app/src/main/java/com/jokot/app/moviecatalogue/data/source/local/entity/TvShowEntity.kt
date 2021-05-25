package com.jokot.app.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowEntity(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val score: Int,
    val posterPath: String?,
    val bannerPath: String?
) : Parcelable
