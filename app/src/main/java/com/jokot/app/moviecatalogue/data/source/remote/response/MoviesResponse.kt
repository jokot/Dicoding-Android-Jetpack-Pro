package com.jokot.app.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class MoviesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movieResults: List<MovieResponse>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)


