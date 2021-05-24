package com.jokot.app.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImagesEntity(
    val backdropSizes: List<String>,
    val baseUrl: String,
    val posterSizes: List<String>,
    val profileSizes: List<String>,
    val secureBaseUrl: String
) : Parcelable
