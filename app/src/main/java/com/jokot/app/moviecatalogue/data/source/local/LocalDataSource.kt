package com.jokot.app.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity
import com.jokot.app.moviecatalogue.data.source.local.room.FilmDao

class LocalDataSource private constructor(private val mFilmDao: FilmDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao: FilmDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao)
    }

    fun getMovieWithDetail(movieId: Int): LiveData<MovieEntity> = mFilmDao.getMovieById(movieId)

    fun getTvShowWithDetail(tvShowId: Int): LiveData<TvShowEntity> = mFilmDao.getTvShowById(tvShowId)

    fun getNowPlayingMovies(): LiveData<List<MovieEntity>> = mFilmDao.getNowPlayingMovies()

    fun getPopularMovies(): LiveData<List<MovieEntity>> = mFilmDao.getPopularMovies()

    fun getTopRatedMovies(): LiveData<List<MovieEntity>> = mFilmDao.getTopRatedMovies()

    fun getUpcomingMovies(): LiveData<List<MovieEntity>> = mFilmDao.getUpcomingMovies()

    fun getOnTheAirTvShows(): LiveData<List<TvShowEntity>> = mFilmDao.getOnTheAirTvShows()

    fun getPopularTvShows(): LiveData<List<TvShowEntity>> = mFilmDao.getPopularTvShows()

    fun getTopRatedTvShows(): LiveData<List<TvShowEntity>> = mFilmDao.getTopRatedTvShows()

    fun getAiringTodayTvShows(): LiveData<List<TvShowEntity>> = mFilmDao.getAiringTodayTvShows()

    private fun insertMovies(movies: List<MovieEntity>) = mFilmDao.insertMovies(movies)

    fun insertOrUpdateNowPlayingMovie(movies: List<MovieEntity>) {
        val savedMovie = ArrayList<MovieEntity>()
        val notSavedMovie = ArrayList<MovieEntity>()
        for (movie in movies) {
            if (getMovieWithDetail(movie.movieId).value != null) {
                savedMovie.add(movie)
            } else {
                notSavedMovie.add(movie)
            }
        }
        setMovieIsNowPlaying(savedMovie)
        insertMovies(notSavedMovie)
    }

    fun insertOrUpdatePopularMovie(movies: List<MovieEntity>) {
        val savedMovie = ArrayList<MovieEntity>()
        val notSavedMovie = ArrayList<MovieEntity>()
        for (movie in movies) {
            if (getMovieWithDetail(movie.movieId).value != null) {
                savedMovie.add(movie)
            } else {
                notSavedMovie.add(movie)
            }
        }
        setMovieIsPopular(savedMovie)
        insertMovies(notSavedMovie)
    }

    fun insertOrUpdateTopRatedMovie(movies: List<MovieEntity>) {
        val savedMovie = ArrayList<MovieEntity>()
        val notSavedMovie = ArrayList<MovieEntity>()
        for (movie in movies) {
            if (getMovieWithDetail(movie.movieId).value != null) {
                savedMovie.add(movie)
            } else {
                notSavedMovie.add(movie)
            }
        }
        setMovieIsTopRated(savedMovie)
        insertMovies(notSavedMovie)
    }

    fun insertOrUpdateUpcomingMovie(movies: List<MovieEntity>) {
        val savedMovie = ArrayList<MovieEntity>()
        val notSavedMovie = ArrayList<MovieEntity>()
        for (movie in movies) {
            if (getMovieWithDetail(movie.movieId).value != null) {
                savedMovie.add(movie)
            } else {
                notSavedMovie.add(movie)
            }
        }
        setMovieIsUpcoming(savedMovie)
        insertMovies(notSavedMovie)
    }

    fun insertTvShow(tvShows: List<TvShowEntity>) = mFilmDao.insertTvShows(tvShows)

    fun getBookmarkedMovie(): LiveData<List<MovieEntity>> = mFilmDao.getBookmarkedMovie()

    fun getBookmarkedTvShow(): LiveData<List<TvShowEntity>> = mFilmDao.getBookmarkedTvShow()

    fun updateMovieDetail(duration: String, genres: String, movieId: Int){
        mFilmDao.updateMovieByDetail(duration, genres,  movieId)
    }

    fun updateTvShowDetail(duration: String, genres: String, tvShowId: Int){
        mFilmDao.updateTvShowByDetail(duration, genres,  tvShowId)
    }


    fun setMovieBookmark(movie: MovieEntity, newState: Boolean) {
        movie.bookmarked = newState
        mFilmDao.updateMovie(movie)
    }

    fun setTvShowBookmark(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.bookmarked = newState
        mFilmDao.updateTvShow(tvShow)
    }

    private fun setMovieIsNowPlaying(movies: List<MovieEntity>) {
        val nowPlayingMovies = ArrayList<MovieEntity>()
        for (movie in movies) {
            movie.isNowPlaying = true
            nowPlayingMovies.add(movie)
        }
        mFilmDao.updateMovies(nowPlayingMovies)
    }
    private fun setMovieIsPopular(movies: List<MovieEntity>) {
        val popularMovies = ArrayList<MovieEntity>()
        for (movie in movies) {
            movie.isPopular = true
            popularMovies.add(movie)
        }
        mFilmDao.updateMovies(popularMovies)
    }
    private fun setMovieIsTopRated(movies: List<MovieEntity>) {
        val topRatedMovies = ArrayList<MovieEntity>()
        for (movie in movies) {
            movie.isTopRated = true
            topRatedMovies.add(movie)
        }
        mFilmDao.updateMovies(topRatedMovies)
    }
    private fun setMovieIsUpcoming(movies: List<MovieEntity>) {
        val upcomingMovies = ArrayList<MovieEntity>()
        for (movie in movies) {
            movie.isUpcoming = true
            upcomingMovies.add(movie)
        }
        mFilmDao.updateMovies(upcomingMovies)
    }
}