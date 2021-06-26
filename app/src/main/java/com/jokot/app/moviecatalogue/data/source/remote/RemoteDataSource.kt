package com.jokot.app.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jokot.app.moviecatalogue.api.ApiConfig
import com.jokot.app.moviecatalogue.api.ApiService
import com.jokot.app.moviecatalogue.data.source.remote.response.*
import com.jokot.app.moviecatalogue.utils.EspressoIdlingResources
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

    fun getConfiguration(): LiveData<ApiResponse<ImagesResponse>> {
        EspressoIdlingResources.increment()
        val resultImage = MutableLiveData<ApiResponse<ImagesResponse>>()
        apiService.getConfiguration(apiKey).enqueue(object : Callback<ConfigurationResponse> {
            override fun onResponse(
                call: Call<ConfigurationResponse>,
                response: Response<ConfigurationResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.images?.let {
                        resultImage.value = ApiResponse.success(it)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<ConfigurationResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResources.decrement()
            }

        })
        return resultImage
    }

    fun getMovieDetail(movieId: Int): LiveData<ApiResponse<MovieDetailResponse>> {
        EspressoIdlingResources.increment()
        val resultMovie = MutableLiveData<ApiResponse<MovieDetailResponse>>()
        apiService.getMovieDetail(movieId, apiKey).enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { resultMovie.value = ApiResponse.success(it) }

                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResources.decrement()
            }
        })
        return resultMovie
    }

    fun getTvShowDetail(tvShowId: Int): LiveData<ApiResponse<TvShowDetailResponse>> {
        EspressoIdlingResources.increment()
        val resultTvShow = MutableLiveData<ApiResponse<TvShowDetailResponse>>()
        apiService.getTvShowDetail(tvShowId, apiKey)
            .enqueue(object : Callback<TvShowDetailResponse> {
                override fun onResponse(
                    call: Call<TvShowDetailResponse>,
                    response: Response<TvShowDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { resultTvShow.value = ApiResponse.success(it) }
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                    EspressoIdlingResources.decrement()
                }

                override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                    EspressoIdlingResources.decrement()
                }
            })
        return resultTvShow
    }

    fun getNowPlayingMovie(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResources.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        apiService.getNowPlayingMovie(apiKey).enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    val movies = response.body()?.movieResults
                    movies?.let { resultMovie.value = ApiResponse.success(movies) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResources.decrement()
            }
        })

        return resultMovie
    }

    fun getPopularMovie(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResources.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        apiService.getPopularMovie(apiKey).enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    val movies = response.body()?.movieResults
                    movies?.let { resultMovie.value = ApiResponse.success(movies) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResources.decrement()
            }
        })
        return resultMovie
    }

    fun getTopRatedMovie(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResources.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        apiService.getTopRatedMovie(apiKey).enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    val movies = response.body()?.movieResults
                    movies?.let { resultMovie.value = ApiResponse.success(movies) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResources.decrement()
            }
        })
        return resultMovie
    }

    fun getUpcomingMovie(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResources.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        apiService.getUpcomingMovie(apiKey).enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    val movies = response.body()?.movieResults
                    movies?.let { resultMovie.value = ApiResponse.success(movies) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResources.decrement()
            }
        })
        return resultMovie
    }

    fun getOnTheAirTvShow(): LiveData<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingResources.increment()
        val resultTvShow = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        apiService.getOnTheAirTvShow(apiKey).enqueue(object : Callback<TvShowsResponse> {
            override fun onResponse(
                call: Call<TvShowsResponse>,
                response: Response<TvShowsResponse>
            ) {
                if (response.isSuccessful) {
                    val tvShows = response.body()?.tvShowResponses
                    tvShows?.let { resultTvShow.value = ApiResponse.success(tvShows) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResources.decrement()
            }
        })

        return resultTvShow
    }

    fun getPopularTvShow(): LiveData<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingResources.increment()
        val resultTvShow = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        apiService.getPopularTvShow(apiKey).enqueue(object : Callback<TvShowsResponse> {
            override fun onResponse(
                call: Call<TvShowsResponse>,
                response: Response<TvShowsResponse>
            ) {
                if (response.isSuccessful) {
                    val tvShows = response.body()?.tvShowResponses
                    tvShows?.let { resultTvShow.value = ApiResponse.success(tvShows) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResources.decrement()
            }
        })
        return resultTvShow
    }

    fun getTopRatedTvShow(): LiveData<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingResources.increment()
        val resultTvShow = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        apiService.getTopRatedTvShow(apiKey).enqueue(object : Callback<TvShowsResponse> {
            override fun onResponse(
                call: Call<TvShowsResponse>,
                response: Response<TvShowsResponse>
            ) {
                if (response.isSuccessful) {
                    val tvShows = response.body()?.tvShowResponses
                    tvShows?.let { resultTvShow.value = ApiResponse.success(tvShows) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResources.decrement()
            }
        })
        return resultTvShow
    }

    fun getAiringTodayTvShow(): LiveData<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingResources.increment()
        val resultTvShow = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        apiService.getAiringTodayTvShow(apiKey).enqueue(object : Callback<TvShowsResponse> {
            override fun onResponse(
                call: Call<TvShowsResponse>,
                response: Response<TvShowsResponse>
            ) {
                if (response.isSuccessful) {
                    val tvShows = response.body()?.tvShowResponses
                    tvShows?.let { resultTvShow.value = ApiResponse.success(tvShows) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResources.decrement()
            }
        })
        return resultTvShow
    }

    interface LoadConfigurationCallback {
        fun onImagesConfigurationReceived(imagesConfig: ImagesResponse)
    }
}