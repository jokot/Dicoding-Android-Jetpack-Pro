package com.jokot.app.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.jokot.app.moviecatalogue.data.source.local.entity.*
import com.jokot.app.moviecatalogue.vo.Resource

interface FilmDataSource {

    fun getConfiguration(): LiveData<ImagesEntity>

    fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getTvShowDetail(tvShowId: Int): LiveData<Resource<TvShowDetailEntity>>

    fun getNowPlayingMovies(): LiveData<Resource<List<MovieEntity>>>

    fun getPopularMovies(): LiveData<Resource<List<MovieEntity>>>

    fun getTopRatedMovies(): LiveData<Resource<List<MovieEntity>>>

    fun getUpcomingMovies(): LiveData<Resource<List<MovieEntity>>>

    fun getOnTheAirTvShows(): LiveData<Resource<List<TvShowEntity>>>

    fun getPopularTvShows(): LiveData<Resource<List<TvShowEntity>>>

    fun getTopRatedTvShows(): LiveData<Resource<List<TvShowEntity>>>

    fun getAiringTodayTvShows(): LiveData<Resource<List<TvShowEntity>>>

    fun getBookmarkedMovie(): LiveData<List<MovieEntity>>

    fun getBookmarkedTvShow(): LiveData<List<TvShowEntity>>

    fun setMovieBookmark(movie: MovieEntity, state: Boolean)

    fun setTvShowBookmark(tvShow: TvShowEntity, state: Boolean)

    fun setTvShowDetailBookmark(tvShow: TvShowDetailEntity, state: Boolean)
}