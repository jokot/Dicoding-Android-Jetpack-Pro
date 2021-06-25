package com.jokot.app.moviecatalogue.data.source.local.entity

import androidx.room.ColumnInfo

data class TvShowDetailEntity(

    @ColumnInfo(name = "episodeRunTime")
    var episodeRunTime: String,

    @ColumnInfo(name = "genres")
    var genres: String,
)
