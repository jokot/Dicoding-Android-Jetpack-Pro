package com.jokot.app.moviecatalogue.data.source.remote

import android.util.Log
import com.jokot.app.moviecatalogue.api.ApiConfig
import com.jokot.app.moviecatalogue.api.ApiService
import com.jokot.app.moviecatalogue.data.source.remote.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(
    private val apiService: ApiService
) {

    companion object {
        const val TAG = "RemoteDataSource"
        val apiKey = ApiConfig.getApiKey()

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiService).apply { instance = this }
            }
    }

    fun getConfiguration(callback: LoadConfigurationCallback) {
        apiService.getConfiguration(apiKey).enqueue(object : Callback<ConfigurationResponse>{
            override fun onResponse(
                call: Call<ConfigurationResponse>,
                response: Response<ConfigurationResponse>
            ) {
                if(response.isSuccessful){
                    response.body()?.images?.let {
                        callback.onImagesConfigurationReceived(it)
                    }
                }else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ConfigurationResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getMovieDetail(movieId: Int, callback: LoadMovieDetailCallback) {
        apiService.getMovieDetail(movieId, apiKey).enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.onMovieDetailReceived(it) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getTvShowDetail(tvShowId: Int, callback: LoadTvShowDetailCallback) {
        apiService.getTvShowDetail(tvShowId, apiKey).enqueue(object : Callback<TvShowDetailResponse> {
            override fun onResponse(
                call: Call<TvShowDetailResponse>,
                response: Response<TvShowDetailResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.onTvShowDetailReceived(it) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getNowPlayingMovie(callback: LoadMoviesCallback) {
        apiService.getNowPlayingMovie(apiKey).enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    val movies = response.body()?.movieResults
                    movies?.let { callback.onAllMovieReceived(it) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getPopularMovie(callback: LoadMoviesCallback) {
        apiService.getPopularMovie(apiKey).enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    val movies = response.body()?.movieResults
                    movies?.let { callback.onAllMovieReceived(it) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getOnTheAirTvShow(callback: LoadTvShowsCallback) {
        apiService.getOnTheAirTvShow(apiKey).enqueue(object : Callback<TvShowsResponse> {
            override fun onResponse(
                call: Call<TvShowsResponse>,
                response: Response<TvShowsResponse>
            ) {
                if (response.isSuccessful) {
                    val tvShows = response.body()?.tvShowResponses
                    tvShows?.let { callback.onAllTvShowReceived(it) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getPopularTvShow(callback: LoadTvShowsCallback) {
        apiService.getPopularTvShow(apiKey).enqueue(object : Callback<TvShowsResponse> {
            override fun onResponse(
                call: Call<TvShowsResponse>,
                response: Response<TvShowsResponse>
            ) {
                if (response.isSuccessful) {
                    val tvShows = response.body()?.tvShowResponses
                    tvShows?.let { callback.onAllTvShowReceived(it) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    interface OnErrorCallback {
        fun onErrorReceived(error: String)
    }

    interface LoadMoviesCallback {
        fun onAllMovieReceived(movieResponses: List<MovieResponse>)
    }

    interface LoadTvShowDetailCallback {
        fun onTvShowDetailReceived(tvShowDetailResponse: TvShowDetailResponse)
    }

    interface LoadMovieDetailCallback {
        fun onMovieDetailReceived(movieDetailResponse: MovieDetailResponse)
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowReceived(tvShowResponses: List<TvShowResponse>)
    }

    interface LoadConfigurationCallback {
        fun onImagesConfigurationReceived(imagesConfiguration: ImagesResponse)
    }
}