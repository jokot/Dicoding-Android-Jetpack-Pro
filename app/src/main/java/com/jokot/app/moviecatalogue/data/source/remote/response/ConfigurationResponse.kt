package com.jokot.app.moviecatalogue.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ConfigurationResponse(
    @SerializedName("images")
    val images: ImagesResponse
)