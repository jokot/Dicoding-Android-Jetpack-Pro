package com.jokot.app.moviecatalogue.data.source.local.entity


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreEntity(
    val id: Int,
    val name: String
) : Parcelable