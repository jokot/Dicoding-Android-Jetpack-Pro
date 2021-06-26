package com.jokot.app.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.jokot.app.moviecatalogue.data.source.local.entity.ImageEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity
import com.jokot.app.moviecatalogue.vo.Resource

interface FilmDataSource {

    fun getConfiguration(): LiveData<Resource<ImageEntity>>

    fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getTvShowDetail(tvShowId: Int): LiveData<Resource<TvShowEntity>>

    fun getNowPlayingMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getPopularMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getTopRatedMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getUpcomingMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getOnTheAirTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getPopularTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getTopRatedTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getAiringTodayTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>>

    fun setMovieFavorite(movie: MovieEntity, state: Boolean)

    fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean)

}