package com.jokot.app.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.jokot.app.moviecatalogue.data.source.local.entity.ImageEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity


@Dao
interface FilmDao {

    @Query("SELECT * FROM image_entities WHERE imageId = 0")
    fun getImage(): LiveData<ImageEntity>

    @Query("SELECT * FROM movie_entities WHERE movieId = :movieId")
    fun getMovieById(movieId: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM tv_show_entities WHERE tvShowId = :tvShowId")
    fun getTvShowById(tvShowId: Int): LiveData<TvShowEntity>

    @Query("SELECT * FROM movie_entities WHERE isNowPlaying = 1")
    fun getNowPlayingMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movie_entities WHERE isPopular = 1")
    fun getPopularMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movie_entities WHERE isTopRated = 1")
    fun getTopRatedMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movie_entities WHERE isUpcoming = 1")
    fun getUpcomingMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_show_entities WHERE isOnAir = 1")
    fun getOnTheAirTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tv_show_entities WHERE isPopular = 1")
    fun getPopularTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tv_show_entities WHERE isTopRated = 1")
    fun getTopRatedTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tv_show_entities WHERE isAiringToday = 1")
    fun getAiringTodayTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM movie_entities WHERE favorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_show_entities WHERE favorite = 1")
    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertImage(image: ImageEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTvShows(tvSows: List<TvShowEntity>)

    @Transaction
    fun insertOrUpdateNowPlayingMovie(movies: List<MovieEntity>, movieIds: List<Int>) {
        insertMovies(movies)
        updateMovieIsNowPlaying(movieIds)
    }

    @Transaction
    fun insertOrUpdatePopularMovie(movies: List<MovieEntity>, movieIds: List<Int>) {
        insertMovies(movies)
        updateMovieIsPopular(movieIds)
    }

    @Transaction
    fun insertOrUpdateTopRatedMovie(movies: List<MovieEntity>, movieIds: List<Int>) {
        insertMovies(movies)
        updateMovieIsTopRated(movieIds)
    }

    @Transaction
    fun insertOrUpdateUpcomingMovie(movies: List<MovieEntity>, movieIds: List<Int>) {
        insertMovies(movies)
        updateMovieIsUpcoming(movieIds)
    }

    @Transaction
    fun insertOrUpdateOnTheAirTvShow(tvShows: List<TvShowEntity>, tvShowIds: List<Int>) {
        insertTvShows(tvShows)
        updateTvShowIsOnTheAir(tvShowIds)
    }

    @Transaction
    fun insertOrUpdatePopularTvShow(tvShows: List<TvShowEntity>, tvShowIds: List<Int>) {
        insertTvShows(tvShows)
        updateTvShowIsPopular(tvShowIds)
    }

    @Transaction
    fun insertOrUpdateTopRatedTvShow(tvShows: List<TvShowEntity>, tvShowIds: List<Int>) {
        insertTvShows(tvShows)
        updateTvShowIsTopRated(tvShowIds)
    }

    @Transaction
    fun insertOrUpdateAiringTodayTvShow(tvShows: List<TvShowEntity>, tvShowIds: List<Int>) {
        insertTvShows(tvShows)
        updateTvShowIsAiringToday(tvShowIds)
    }

    @Update
    fun updateMovie(movie: MovieEntity)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)

    @Query("UPDATE movie_entities SET duration = :duration, genres = :genres WHERE movieId = :movieId")
    fun updateMovieByDetail(duration: String, genres: String, movieId: Int)

    @Query("UPDATE tv_show_entities SET episodeRunTime = :duration, genres = :genres WHERE tvShowId = :tvShowId")
    fun updateTvShowByDetail(duration: String, genres: String, tvShowId: Int)

    @Query("UPDATE movie_entities SET isNowPlaying = 1 WHERE movieId IN(:movieIds)")
    fun updateMovieIsNowPlaying(movieIds: List<Int>)

    @Query("UPDATE movie_entities SET isPopular = 1 WHERE movieId IN(:movieIds)")
    fun updateMovieIsPopular(movieIds: List<Int>)

    @Query("UPDATE movie_entities SET isTopRated = 1 WHERE movieId IN(:movieIds)")
    fun updateMovieIsTopRated(movieIds: List<Int>)

    @Query("UPDATE movie_entities SET isUpcoming = 1 WHERE movieId IN(:movieIds)")
    fun updateMovieIsUpcoming(movieIds: List<Int>)

    @Query("UPDATE tv_show_entities SET isOnAir = 1 WHERE tvShowId IN(:tvShowIds)")
    fun updateTvShowIsOnTheAir(tvShowIds: List<Int>)

    @Query("UPDATE tv_show_entities SET isPopular = 1 WHERE tvShowId IN(:tvShowIds)")
    fun updateTvShowIsPopular(tvShowIds: List<Int>)

    @Query("UPDATE tv_show_entities SET isTopRated = 1 WHERE tvShowId IN(:tvShowIds)")
    fun updateTvShowIsTopRated(tvShowIds: List<Int>)

    @Query("UPDATE tv_show_entities SET isAiringToday = 1 WHERE tvShowId IN(:tvShowIds)")
    fun updateTvShowIsAiringToday(tvShowIds: List<Int>)
}