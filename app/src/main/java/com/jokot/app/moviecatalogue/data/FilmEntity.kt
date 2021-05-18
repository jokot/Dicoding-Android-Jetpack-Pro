package com.jokot.app.moviecatalogue.data

data class FilmEntity(
    val id: String,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val score: Int,
    val genre: String,
    val duration: String,
    val posterPath: String,
    val bannerPath: String
)
