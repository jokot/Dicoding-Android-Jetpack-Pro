package com.jokot.app.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity


@Dao
interface FilmDao {

    @Query("SELECT * FROM movie_entities WHERE movieId = :movieId")
    fun getMovieById(movieId: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM tv_show_entities WHERE tvShowId = :tvShowId")
    fun getTvShowById(tvShowId: Int): LiveData<TvShowEntity>

    @Query("SELECT * FROM movie_entities WHERE isNowPlaying = 1")
    fun getNowPlayingMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie_entities WHERE isPopular = 1")
    fun getPopularMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie_entities WHERE isTopRated = 1")
    fun getTopRatedMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie_entities WHERE isUpcoming = 1")
    fun getUpcomingMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM tv_show_entities WHERE isOnAir = 1")
    fun getOnTheAirTvShows(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM tv_show_entities WHERE isPopular = 1")
    fun getPopularTvShows(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM tv_show_entities WHERE isTopRated = 1")
    fun getTopRatedTvShows(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM tv_show_entities WHERE isAiringToday = 1")
    fun getAiringTodayTvShows(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM movie_entities WHERE bookmarked = 1")
    fun getBookmarkedMovie(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM tv_show_entities WHERE bookmarked = 1")
    fun getBookmarkedTvShow(): LiveData<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTvShows(tvSows: List<TvShowEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Update
    fun updateMovies(movies: List<MovieEntity>)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)

    @Query("UPDATE movie_entities SET duration = :duration, genres = :genres WHERE movieId = :movieId")
    fun updateMovieByDetail(duration: String, genres: String, movieId: Int)

    @Query("UPDATE tv_show_entities SET episodeRunTime = :duration, genres = :genres WHERE tvShowId = :tvShowId")
    fun updateTvShowByDetail(duration: String, genres: String, tvShowId: Int)

}