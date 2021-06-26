package com.jokot.app.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.jokot.app.moviecatalogue.data.source.local.entity.ImagesEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity
import com.jokot.app.moviecatalogue.vo.Resource

interface FilmDataSource {

    fun getConfiguration(): LiveData<ImagesEntity>

    fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getTvShowDetail(tvShowId: Int): LiveData<Resource<TvShowEntity>>

    fun getNowPlayingMovies(): LiveData<Resource<List<MovieEntity>>>

    fun getPopularMovies(): LiveData<Resource<List<MovieEntity>>>

    fun getTopRatedMovies(): LiveData<Resource<List<MovieEntity>>>

    fun getUpcomingMovies(): LiveData<Resource<List<MovieEntity>>>

    fun getOnTheAirTvShows(): LiveData<Resource<List<TvShowEntity>>>

    fun getPopularTvShows(): LiveData<Resource<List<TvShowEntity>>>

    fun getTopRatedTvShows(): LiveData<Resource<List<TvShowEntity>>>

    fun getAiringTodayTvShows(): LiveData<Resource<List<TvShowEntity>>>

    fun getFavoriteMovie(): LiveData<List<MovieEntity>>

    fun getFavoriteTvShow(): LiveData<List<TvShowEntity>>

    fun setMovieFavorite(movie: MovieEntity, state: Boolean)

    fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean)

}