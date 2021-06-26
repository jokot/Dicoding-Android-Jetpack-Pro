package com.jokot.app.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import com.jokot.app.moviecatalogue.data.source.local.entity.ImageEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity
import com.jokot.app.moviecatalogue.data.source.local.room.FilmDao

class LocalDataSource private constructor(private val mFilmDao: FilmDao) {
    companion object {
        const val TAG = "LocalDataSource"
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao: FilmDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao)
    }

    fun getImage(): LiveData<ImageEntity> = mFilmDao.getImage()

    fun getMovieWithDetail(movieId: Int): LiveData<MovieEntity> = mFilmDao.getMovieById(movieId)

    fun getTvShowWithDetail(tvShowId: Int): LiveData<TvShowEntity> =
        mFilmDao.getTvShowById(tvShowId)

    fun getNowPlayingMovies(): LiveData<List<MovieEntity>> = mFilmDao.getNowPlayingMovies()

    fun getPopularMovies(): LiveData<List<MovieEntity>> = mFilmDao.getPopularMovies()

    fun getTopRatedMovies(): LiveData<List<MovieEntity>> = mFilmDao.getTopRatedMovies()

    fun getUpcomingMovies(): LiveData<List<MovieEntity>> = mFilmDao.getUpcomingMovies()

    fun getOnTheAirTvShows(): LiveData<List<TvShowEntity>> = mFilmDao.getOnTheAirTvShows()

    fun getPopularTvShows(): LiveData<List<TvShowEntity>> = mFilmDao.getPopularTvShows()

    fun getTopRatedTvShows(): LiveData<List<TvShowEntity>> = mFilmDao.getTopRatedTvShows()

    fun getAiringTodayTvShows(): LiveData<List<TvShowEntity>> = mFilmDao.getAiringTodayTvShows()

    fun insertImage(image: ImageEntity) =
        mFilmDao.insertImage(image)

    fun insertOrUpdateNowPlayingMovie(movies: List<MovieEntity>, movieIds: List<Int>) =
        mFilmDao.insertOrUpdateNowPlayingMovie(movies, movieIds)

    fun insertOrUpdatePopularMovie(movies: List<MovieEntity>, movieIds: List<Int>) =
        mFilmDao.insertOrUpdatePopularMovie(movies, movieIds)

    fun insertOrUpdateTopRatedMovie(movies: List<MovieEntity>, movieIds: List<Int>) =
        mFilmDao.insertOrUpdateTopRatedMovie(movies, movieIds)

    fun insertOrUpdateUpcomingMovie(movies: List<MovieEntity>, movieIds: List<Int>) =
        mFilmDao.insertOrUpdateUpcomingMovie(movies, movieIds)

    fun insertOrUpdateOnTheAirTvShow(tvShows: List<TvShowEntity>, tvShowIds: List<Int>) =
        mFilmDao.insertOrUpdateOnTheAirTvShow(tvShows, tvShowIds)

    fun insertOrUpdatePopularTvShow(tvShows: List<TvShowEntity>, tvShowIds: List<Int>) =
        mFilmDao.insertOrUpdatePopularTvShow(tvShows, tvShowIds)

    fun insertOrUpdateTopRatedTvShow(tvShows: List<TvShowEntity>, tvShowIds: List<Int>) =
        mFilmDao.insertOrUpdateTopRatedTvShow(tvShows, tvShowIds)

    fun insertOrUpdateAiringTodayTvShow(tvShows: List<TvShowEntity>, tvShowIds: List<Int>) =
        mFilmDao.insertOrUpdateAiringTodayTvShow(tvShows, tvShowIds)

    fun getFavoriteMovie(): LiveData<List<MovieEntity>> = mFilmDao.getFavoriteMovie()

    fun getFavoriteTvShow(): LiveData<List<TvShowEntity>> = mFilmDao.getFavoriteTvShow()

    fun updateMovieDetail(duration: String, genres: String, movieId: Int) {
        mFilmDao.updateMovieByDetail(duration, genres, movieId)
    }

    fun updateTvShowDetail(duration: String, genres: String, tvShowId: Int) {
        mFilmDao.updateTvShowByDetail(duration, genres, tvShowId)
    }


    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.favorite = newState
        mFilmDao.updateMovie(movie)
    }

    fun setTvShowFavorite(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.favorite = newState
        mFilmDao.updateTvShow(tvShow)
    }
}