package com.jokot.app.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonEntity(
    val adult: Boolean?,
    val alsoKnownAs: List<String>?,
    val biography: String?,
    val birthday: String?,
    val gender: Int?,
    val id: Int?,
    val imdbId: String?,
    val knownForDepartment: String?,
    val name: String?,
    val placeOfBirth: String?,
    val popularity: Double?,
    val profilePath: String?
) : Parcelable
