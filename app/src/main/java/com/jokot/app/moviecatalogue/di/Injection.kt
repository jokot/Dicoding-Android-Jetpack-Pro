package com.jokot.app.moviecatalogue.di

import android.content.Context
import com.jokot.app.moviecatalogue.api.ApiConfig
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.LocalDataSource
import com.jokot.app.moviecatalogue.data.source.local.room.FilmDatabase
import com.jokot.app.moviecatalogue.data.source.remote.RemoteDataSource
import com.jokot.app.moviecatalogue.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): FilmRepository {

        val database = FilmDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.getApiService())
        val localDataSource = LocalDataSource.getInstance(database.filmDao())
        val appExecutors = AppExecutors()

        return FilmRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}