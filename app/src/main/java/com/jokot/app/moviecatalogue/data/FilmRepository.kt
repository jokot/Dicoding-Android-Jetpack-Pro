package com.jokot.app.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jokot.app.moviecatalogue.data.source.local.LocalDataSource
import com.jokot.app.moviecatalogue.data.source.local.entity.*
import com.jokot.app.moviecatalogue.data.source.remote.ApiResponse
import com.jokot.app.moviecatalogue.data.source.remote.RemoteDataSource
import com.jokot.app.moviecatalogue.data.source.remote.RemoteDataSource.*
import com.jokot.app.moviecatalogue.data.source.remote.response.*
import com.jokot.app.moviecatalogue.utils.AppExecutors
import com.jokot.app.moviecatalogue.utils.convertDateFormat
import com.jokot.app.moviecatalogue.utils.convertRunTimeToDuration
import com.jokot.app.moviecatalogue.vo.Resource

class FilmRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    FilmDataSource {

    companion object {
        @Volatile
        private var instance: FilmRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): FilmRepository =
            instance ?: synchronized(this) {
                FilmRepository(remoteData, localData, appExecutors).apply { instance = this }
            }
    }

    override fun getConfiguration(): LiveData<ImagesEntity> {
        val imagesResult = MutableLiveData<ImagesEntity>()

        remoteDataSource.getConfiguration(object : LoadConfigurationCallback {
            override fun onImagesConfigurationReceived(imagesConfig: ImagesResponse) {
                imagesConfig.let {
                    val images = ImagesEntity(
                        it.backdropSizes,
                        it.baseUrl,
                        it.posterSizes,
                        it.profileSizes,
                        it.secureBaseUrl
                    )
                    imagesResult.postValue(images)
                }
            }

        })

        return imagesResult
    }

    override fun getMovieDetail(movieId: Int): LiveData<Resource<MovieDetailEntity>> {
        return object : NetworkBoundResource<MovieDetailEntity, MovieDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieDetailEntity> =
                localDataSource.getMovieDetail(movieId)

            override fun shouldFetch(data: MovieDetailEntity?): Boolean =
                data == null


            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> =
                remoteDataSource.getMovieDetail(movieId)


            override fun saveCallResult(movieDetailResponse: MovieDetailResponse) {
                val genres = ArrayList<String>()
                for (genre in movieDetailResponse.genres) {
                    genres.add(genre.name)
                }
                val movie = MovieDetailEntity(
                    movieDetailResponse.adult,
                    movieDetailResponse.backdropPath,
                    movieDetailResponse.budget,
                    genres.joinToString(),
                    movieDetailResponse.homepage,
                    movieDetailResponse.id,
                    movieDetailResponse.imdbId,
                    movieDetailResponse.originalLanguage,
                    movieDetailResponse.originalTitle,
                    movieDetailResponse.overview,
                    movieDetailResponse.popularity,
                    movieDetailResponse.posterPath,
                    convertDateFormat(movieDetailResponse.releaseDate),
                    movieDetailResponse.revenue,
                    convertRunTimeToDuration(movieDetailResponse.runtime),
                    movieDetailResponse.status,
                    movieDetailResponse.tagline,
                    movieDetailResponse.title,
                    movieDetailResponse.video,
                    (movieDetailResponse.voteAverage * 10).toInt(),
                    movieDetailResponse.voteCount
                )
                localDataSource.insertMovieDetail(movie)

            }

        }.asLiveData()
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<Resource<TvShowDetailEntity>> {
        return object :
            NetworkBoundResource<TvShowDetailEntity, TvShowDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowDetailEntity> =
                localDataSource.getTvShowDetail(tvShowId)

            override fun shouldFetch(data: TvShowDetailEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<TvShowDetailResponse>> =
                remoteDataSource.getTvShowDetail(tvShowId)


            override fun saveCallResult(tvShowDetailResponse: TvShowDetailResponse) {
                val genres = ArrayList<String>()
                for (genre in tvShowDetailResponse.genres) {
                    genres.add(genre.name)
                }
                val tvShow = TvShowDetailEntity(
                    tvShowDetailResponse.backdropPath,
                    convertRunTimeToDuration(tvShowDetailResponse.episodeRunTime.average().toInt()),
                    convertDateFormat(tvShowDetailResponse.firstAirDate),
                    genres.joinToString(),
                    tvShowDetailResponse.homepage,
                    tvShowDetailResponse.id,
                    tvShowDetailResponse.inProduction,
                    convertDateFormat(tvShowDetailResponse.lastAirDate),
                    tvShowDetailResponse.name,
                    tvShowDetailResponse.numberOfEpisodes,
                    tvShowDetailResponse.numberOfSeasons,
                    tvShowDetailResponse.originalLanguage,
                    tvShowDetailResponse.originalName,
                    tvShowDetailResponse.overview,
                    tvShowDetailResponse.popularity,
                    tvShowDetailResponse.posterPath,
                    tvShowDetailResponse.status,
                    tvShowDetailResponse.tagline,
                    tvShowDetailResponse.type,
                    (tvShowDetailResponse.voteAverage * 10).toInt(),
                    tvShowDetailResponse.voteCount
                )

                localDataSource.insertTvShowDetail(tvShow)
            }

        }.asLiveData()
    }

    override fun getNowPlayingMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object: NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> =
                localDataSource.getNowPlayingMovies()

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getNowPlayingMovie()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.overview,
                        convertDateFormat(response.releaseDate),
                        response.voteAverage.toInt(),
                        response.posterPath,
                        response.backdropPath,
                        isNowPlaying = true
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }

        }.asLiveData()
    }

    override fun getPopularMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> =
                localDataSource.getPopularMovies()

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getPopularMovie()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.overview,
                        convertDateFormat(response.releaseDate),
                        response.voteAverage.toInt(),
                        response.posterPath,
                        response.backdropPath,
                        isPopular = true
                    )
                    movieList.add(movie)
                }

                localDataSource.insertMovies(movieList)
            }

        }.asLiveData()
    }

    override fun getTopRatedMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> =
                localDataSource.getTopRatedMovies()

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getTopRatedMovie()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.overview,
                        convertDateFormat(response.releaseDate),
                        response.voteAverage.toInt(),
                        response.posterPath,
                        response.backdropPath,
                        isTopRated = true
                    )
                    movieList.add(movie)
                }

                localDataSource.insertMovies(movieList)
            }

        }.asLiveData()
    }

    override fun getUpcomingMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> =
                localDataSource.getUpcomingMovies()

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getUpcomingMovie()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.overview,
                        convertDateFormat(response.releaseDate),
                        response.voteAverage.toInt(),
                        response.posterPath,
                        response.backdropPath,
                        isUpcoming = true
                    )
                    movieList.add(movie)
                }

                localDataSource.insertMovies(movieList)
            }

        }.asLiveData()
    }

    override fun getOnTheAirTvShows(): LiveData<Resource<List<TvShowEntity>>> {
        return object : NetworkBoundResource<List<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvShowEntity>> =
                localDataSource.getOnTheAirTvShows()

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getOnTheAirTvShow()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.name,
                        response.overview,
                        convertDateFormat(response.firstAirDate),
                        response.voteAverage.toInt(),
                        response.posterPath,
                        response.backdropPath,
                        isOnAir = true
                    )
                    tvShowList.add(tvShow)
                }

                localDataSource.insertTvShow(tvShowList)
            }
        }.asLiveData()
    }

    override fun getPopularTvShows(): LiveData<Resource<List<TvShowEntity>>> {
        return object : NetworkBoundResource<List<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvShowEntity>> =
                localDataSource.getPopularTvShows()

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getPopularTvShow()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.name,
                        response.overview,
                        convertDateFormat(response.firstAirDate),
                        response.voteAverage.toInt(),
                        response.posterPath,
                        response.backdropPath,
                        isPopular = true
                    )
                    tvShowList.add(tvShow)
                }

                localDataSource.insertTvShow(tvShowList)
            }
        }.asLiveData()
    }

    override fun getTopRatedTvShows(): LiveData<Resource<List<TvShowEntity>>> {
        return object : NetworkBoundResource<List<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvShowEntity>> =
                localDataSource.getTopRatedTvShows()

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getTopRatedTvShow()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.name,
                        response.overview,
                        convertDateFormat(response.firstAirDate),
                        response.voteAverage.toInt(),
                        response.posterPath,
                        response.backdropPath,
                        isTopRated = true
                    )
                    tvShowList.add(tvShow)
                }

                localDataSource.insertTvShow(tvShowList)
            }
        }.asLiveData()
    }

    override fun getAiringTodayTvShows(): LiveData<Resource<List<TvShowEntity>>> {
        return object : NetworkBoundResource<List<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvShowEntity>> =
                localDataSource.getAiringTodayTvShows()

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getAiringTodayTvShow()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.name,
                        response.overview,
                        convertDateFormat(response.firstAirDate),
                        response.voteAverage.toInt(),
                        response.posterPath,
                        response.backdropPath,
                        isAiringToday = true
                    )
                    tvShowList.add(tvShow)
                }

                localDataSource.insertTvShow(tvShowList)
            }
        }.asLiveData()
    }

    override fun getBookmarkedMovie(): LiveData<List<MovieEntity>> =
        localDataSource.getBookmarkedMovie()

    override fun getBookmarkedTvShow(): LiveData<List<TvShowEntity>> =
        localDataSource.getBookmarkedTvShow()

    override fun setMovieBookmark(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setMovieBookmark(movie,state) }

    override fun setTvShowBookmark(tvShow: TvShowEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setTvShowBookmark(tvShow, state) }

    override fun setMovieDetailBookmark(movie: MovieDetailEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setMovieDetailBookmark(movie,state) }
    }

    override fun setTvShowDetailBookmark(tvShow: TvShowDetailEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setTvShowDetailBookmark(tvShow,state) }
    }
}