package com.jokot.app.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieDetailEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowDetailEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity
import com.jokot.app.moviecatalogue.data.source.local.room.FilmDao

class LocalDataSource private constructor(private val mFilmDao: FilmDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao: FilmDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao)
    }

    fun getMovieDetail(movieId: Int): LiveData<MovieDetailEntity> = mFilmDao.getMovieDetail(movieId)

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowDetailEntity> = mFilmDao.getTvShowDetail(tvShowId)

    fun getNowPlayingMovies(): LiveData<List<MovieEntity>> = mFilmDao.getNowPlayingMovies()

    fun getPopularMovies(): LiveData<List<MovieEntity>> = mFilmDao.getPopularMovies()

    fun getTopRatedMovies(): LiveData<List<MovieEntity>> = mFilmDao.getTopRatedMovies()

    fun getUpcomingMovies(): LiveData<List<MovieEntity>> = mFilmDao.getUpcomingMovies()

    fun getOnTheAirTvShows(): LiveData<List<TvShowEntity>> = mFilmDao.getOnTheAirTvShows()

    fun getPopularTvShows(): LiveData<List<TvShowEntity>> = mFilmDao.getPopularTvShows()

    fun getTopRatedTvShows(): LiveData<List<TvShowEntity>> = mFilmDao.getTopRatedTvShows()

    fun getAiringTodayTvShows(): LiveData<List<TvShowEntity>> = mFilmDao.getAiringTodayTvShows()

    fun insertMovies(movies: List<MovieEntity>) = mFilmDao.insertMovies(movies)

    fun insertTvShow(tvShows: List<TvShowEntity>) = mFilmDao.insertTvShows(tvShows)

    fun insertMovieDetail(movie: MovieDetailEntity) = mFilmDao.insertMovieDetail(movie)

    fun insertTvShowDetail(tvShow: TvShowDetailEntity) = mFilmDao.insertTvShowDetail(tvShow)

    fun getBookmarkedMovie(): LiveData<List<MovieEntity>> = mFilmDao.getBookmarkedMovie()

    fun getBookmarkedTvShow(): LiveData<List<TvShowEntity>> = mFilmDao.getBookmarkedTvShow()

    fun setMovieBookmark(movie: MovieEntity, newState: Boolean) {
        movie.bookmarked = newState
        mFilmDao.updateMovie(movie)
    }

    fun setTvShowBookmark(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.bookmarked = newState
        mFilmDao.updateTvShow(tvShow)
    }

    fun setMovieDetailBookmark(movie: MovieDetailEntity, newState: Boolean) {
        movie.bookmarked = newState
        mFilmDao.updateMovieDetail(movie)
    }

    fun setTvShowDetailBookmark(tvShow: TvShowDetailEntity, newState: Boolean) {
        tvShow.bookmarked = newState
        mFilmDao.updateTvShowDetail(tvShow)
    }
}