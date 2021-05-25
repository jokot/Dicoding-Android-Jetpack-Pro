package com.jokot.app.moviecatalogue.di

import com.jokot.app.moviecatalogue.api.ApiConfig
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.remote.RemoteDataSource

object Injection {

    fun provideRepository(): FilmRepository {

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.getApiService())

        return FilmRepository.getInstance(remoteDataSource)
    }
}