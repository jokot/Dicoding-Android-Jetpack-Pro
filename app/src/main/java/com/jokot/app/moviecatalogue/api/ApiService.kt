package com.jokot.app.moviecatalogue.api

import com.jokot.app.moviecatalogue.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("configuration")
    fun getConfiguration(
        @Query("api_key") apiKey: String
    ): Call<ConfigurationResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") lang: String? = null
    ): Call<MovieDetailResponse>

    @GET("tv/{tv_id}")
    fun getTvShowDetail(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") lang: String? = null
    ): Call<TvShowDetailResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovie(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String? = null,
        @Query("page") page: Int? = null
    ): Call<MoviesResponse>

    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String? = null,
        @Query("page") page: Int? = null
    ): Call<MoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovie(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String? = null,
        @Query("page") page: Int? = null
    ): Call<MoviesResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovie(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String? = null,
        @Query("page") page: Int? = null
    ): Call<MoviesResponse>

    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") lang: String? = null
    ): Call<CreditsResponse>

    @GET("tv/on_the_air")
    fun getOnTheAirTvShow(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String? = null,
        @Query("page") page: Int? = null
    ): Call<TvShowsResponse>

    @GET("tv/popular")
    fun getPopularTvShow(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String? = null,
        @Query("page") page: Int? = null
    ): Call<TvShowsResponse>

    @GET("tv/top_rated")
    fun getTopRatedTvShow(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String? = null,
        @Query("page") page: Int? = null
    ): Call<TvShowsResponse>

    @GET("tv/airing_today")
    fun getAiringTodayTvShow(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String? = null,
        @Query("page") page: Int? = null
    ): Call<TvShowsResponse>

    @GET("tv/{tv_id}/credits")
    fun getTvShowCredits(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") lang: String? = null
    ): Call<CreditsResponse>

    @GET("person/{person_id}")
    fun getPersonDetail(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") lang: String? = null
    ): Call<PersonResponse>
}