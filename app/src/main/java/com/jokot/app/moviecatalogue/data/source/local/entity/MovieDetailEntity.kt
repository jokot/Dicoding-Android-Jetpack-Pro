package com.jokot.app.moviecatalogue.data.source.local.entity

import androidx.room.ColumnInfo

data class MovieDetailEntity(
    @ColumnInfo(name = "duration")
    var duration: String?,

    @ColumnInfo(name = "genres")
    var genres: String?,
)
