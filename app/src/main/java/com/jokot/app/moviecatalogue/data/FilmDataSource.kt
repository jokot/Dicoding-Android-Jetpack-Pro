package com.jokot.app.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.jokot.app.moviecatalogue.data.source.local.entity.*

interface FilmDataSource {

    fun getConfiguration(): LiveData<ImagesEntity>

    fun getMovieDetail(movieId: Int): LiveData<MovieDetailEntity>

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowDetailEntity>

    fun getNowPlayingMovies(): LiveData<List<MovieEntity>>

    fun getPopularMovies(): LiveData<List<MovieEntity>>

    fun getTopRatedMovies(): LiveData<List<MovieEntity>>

    fun getUpcomingMovies(): LiveData<List<MovieEntity>>

    fun getOnTheAirTvShows(): LiveData<List<TvShowEntity>>

    fun getPopularTvShows(): LiveData<List<TvShowEntity>>

    fun getTopRatedTvShows(): LiveData<List<TvShowEntity>>

    fun getAiringTodayTvShows(): LiveData<List<TvShowEntity>>
}