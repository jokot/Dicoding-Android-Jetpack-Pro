package com.jokot.app.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowsResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val tvShowResponses: List<TvShowResponse>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)

