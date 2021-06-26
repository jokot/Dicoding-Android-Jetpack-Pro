package com.jokot.app.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jokot.app.moviecatalogue.data.source.local.LocalDataSource
import com.jokot.app.moviecatalogue.data.source.local.entity.ImageEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity
import com.jokot.app.moviecatalogue.data.source.remote.ApiResponse
import com.jokot.app.moviecatalogue.data.source.remote.RemoteDataSource
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

    override fun getConfiguration(): LiveData<Resource<ImageEntity>> {
        return object : NetworkBoundResource<ImageEntity, ImagesResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<ImageEntity> =
                localDataSource.getImage()

            override fun shouldFetch(data: ImageEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<ImagesResponse>> =
                remoteDataSource.getConfiguration()

            override fun saveCallResult(data: ImagesResponse) {
                val images = ImageEntity(
                    data.backdropSizes[1],
                    data.posterSizes[0],
                    data.secureBaseUrl
                )

                localDataSource.insertImage(images)
            }

        }.asLiveData()
    }

    override fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getMovieWithDetail(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data?.movieDetailEntity == null


            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> =
                remoteDataSource.getMovieDetail(movieId)


            override fun saveCallResult(movieDetailResponse: MovieDetailResponse) {
                val genres = ArrayList<String>()
                for (genre in movieDetailResponse.genres) {
                    genres.add(genre.name)
                }

                appExecutors.diskIO().execute {
                    localDataSource.updateMovieDetail(
                        convertRunTimeToDuration(movieDetailResponse.runtime),
                        genres.joinToString(),
                        movieId
                    )
                }
            }

        }.asLiveData()
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShowDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getTvShowWithDetail(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data?.tvShowDetailEntity == null

            override fun createCall(): LiveData<ApiResponse<TvShowDetailResponse>> =
                remoteDataSource.getTvShowDetail(tvShowId)


            override fun saveCallResult(tvShowDetailResponse: TvShowDetailResponse) {
                val genres = ArrayList<String>()
                for (genre in tvShowDetailResponse.genres) {
                    genres.add(genre.name)
                }

                appExecutors.diskIO().execute {
                    localDataSource.updateTvShowDetail(
                        convertRunTimeToDuration(
                            tvShowDetailResponse.episodeRunTime.average().toInt()
                        ),
                        genres.joinToString(),
                        tvShowId
                    )
                }
            }

        }.asLiveData()
    }

    override fun getNowPlayingMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getNowPlayingMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getNowPlayingMovie()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                val movieIds = ArrayList<Int>()
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
                    movieIds.add(movie.movieId)
                }

                localDataSource.insertOrUpdateNowPlayingMovie(movieList, movieIds)
            }

        }.asLiveData()
    }

    override fun getPopularMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getPopularMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getPopularMovie()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                val movieIds = ArrayList<Int>()
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
                    movieIds.add(movie.movieId)
                }
                localDataSource.insertOrUpdatePopularMovie(movieList, movieIds)

            }

        }.asLiveData()
    }

    override fun getTopRatedMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getTopRatedMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getTopRatedMovie()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                val movieIds = ArrayList<Int>()
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
                    movieIds.add(movie.movieId)
                }
                localDataSource.insertOrUpdateTopRatedMovie(movieList, movieIds)
            }

        }.asLiveData()
    }

    override fun getUpcomingMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getUpcomingMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getUpcomingMovie()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                val movieIds = ArrayList<Int>()
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
                    movieIds.add(movie.movieId)
                }

                localDataSource.insertOrUpdateUpcomingMovie(movieList, movieIds)
            }

        }.asLiveData()
    }

    override fun getOnTheAirTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getOnTheAirTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getOnTheAirTvShow()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                val tvShowIds = ArrayList<Int>()
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
                    tvShowIds.add(tvShow.id)
                }

                localDataSource.insertOrUpdateOnTheAirTvShow(tvShowList, tvShowIds)
            }
        }.asLiveData()
    }

    override fun getPopularTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getPopularTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getPopularTvShow()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                val tvShowIds = ArrayList<Int>()
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
                    tvShowIds.add(tvShow.id)
                }

                localDataSource.insertOrUpdatePopularTvShow(tvShowList, tvShowIds)
            }
        }.asLiveData()
    }

    override fun getTopRatedTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getTopRatedTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getTopRatedTvShow()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                val tvShowIds = ArrayList<Int>()
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
                    tvShowIds.add(tvShow.id)
                }

                localDataSource.insertOrUpdateTopRatedTvShow(tvShowList, tvShowIds)
            }
        }.asLiveData()
    }

    override fun getAiringTodayTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAiringTodayTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getAiringTodayTvShow()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                val tvShowIds = ArrayList<Int>()
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
                    tvShowIds.add(tvShow.id)
                }

                localDataSource.insertOrUpdateAiringTodayTvShow(tvShowList, tvShowIds)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShow(), config).build()
    }

    override fun setMovieFavorite(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movie, state) }

    override fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setTvShowFavorite(tvShow, state) }

}