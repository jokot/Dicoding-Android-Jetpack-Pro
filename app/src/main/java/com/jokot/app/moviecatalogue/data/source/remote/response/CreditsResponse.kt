package com.jokot.app.moviecatalogue.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class CreditsResponse(
    @SerializedName("cast")
    val castResponse: List<CastResponse>?,
    @SerializedName("crew")
    val crewResponse: List<CrewResponse>?,
    @SerializedName("id")
    val id: Int?
)