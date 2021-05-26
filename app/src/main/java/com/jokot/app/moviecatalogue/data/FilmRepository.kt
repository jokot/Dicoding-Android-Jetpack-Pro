package com.jokot.app.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jokot.app.moviecatalogue.data.source.local.entity.*
import com.jokot.app.moviecatalogue.data.source.remote.RemoteDataSource
import com.jokot.app.moviecatalogue.data.source.remote.RemoteDataSource.*
import com.jokot.app.moviecatalogue.data.source.remote.response.*
import com.jokot.app.moviecatalogue.utils.convertDateFormat
import com.jokot.app.moviecatalogue.utils.convertRunTimeToDuration

class FilmRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    FilmDataSource {

    companion object {
        @Volatile
        private var instance: FilmRepository? = null

        fun getInstance(remoteData: RemoteDataSource): FilmRepository =
            instance ?: synchronized(this) {
                FilmRepository(remoteData).apply { instance = this }
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

    override fun getMovieDetail(movieId: Int): LiveData<MovieDetailEntity> {
        val movieResult = MutableLiveData<MovieDetailEntity>()

        remoteDataSource.getMovieDetail(movieId, object : LoadMovieDetailCallback {
            override fun onMovieDetailReceived(movieDetailResponse: MovieDetailResponse) {
                val genres = ArrayList<String>()
                for (genre in movieDetailResponse.genres) {
                    genres.add(genre.name)
                }
                val movie = MovieDetailEntity(
                    movieDetailResponse.adult,
                    movieDetailResponse.backdropPath,
                    movieDetailResponse.budget,
                    genres,
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
                movieResult.postValue(movie)
            }
        })

        return movieResult
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<TvShowDetailEntity> {
        val tvShowResult = MutableLiveData<TvShowDetailEntity>()

        remoteDataSource.getTvShowDetail(tvShowId, object : LoadTvShowDetailCallback {
            override fun onTvShowDetailReceived(tvShowDetailResponse: TvShowDetailResponse) {
                val genres = ArrayList<String>()
                for (genre in tvShowDetailResponse.genres) {
                    genres.add(genre.name)
                }

                val seasons = ArrayList<SeasonEntity>()
                for (season in tvShowDetailResponse.seasons) {
                    seasons.add(
                        SeasonEntity(
                            season.airDate?.let { convertDateFormat(it) },
                            season.episodeCount,
                            season.id,
                            season.name,
                            season.overview,
                            season.posterPath,
                            season.seasonNumber
                        )
                    )
                }

                tvShowDetailResponse.let {
                    val tvShow = TvShowDetailEntity(
                        it.backdropPath,
                        convertRunTimeToDuration(it.episodeRunTime.average().toInt()),
                        convertDateFormat(it.firstAirDate),
                        genres,
                        it.homepage,
                        it.id,
                        it.inProduction,
                        it.languages,
                        convertDateFormat(it.lastAirDate),
                        it.name,
                        it.numberOfEpisodes,
                        it.numberOfSeasons,
                        it.originalLanguage,
                        it.originalName,
                        it.overview,
                        it.popularity,
                        it.posterPath,
                        seasons,
                        it.status,
                        it.tagline,
                        it.type,
                        (it.voteAverage * 10).toInt(),
                        it.voteCount
                    )
                    tvShowResult.postValue(tvShow)
                }
            }
        })

        return tvShowResult
    }

    override fun getNowPlayingMovies(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getNowPlayingMovie(object : LoadMoviesCallback {
            override fun onAllMovieReceived(movieResponses: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponses) {
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.overview,
                        convertDateFormat(response.releaseDate),
                        response.voteAverage.toInt(),
                        response.posterPath,
                        response.backdropPath
                    )
                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        })

        return movieResults
    }

    override fun getPopularMovies(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getPopularMovie(object : LoadMoviesCallback {
            override fun onAllMovieReceived(movieResponses: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponses) {
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.overview,
                        convertDateFormat(response.releaseDate),
                        response.voteAverage.toInt(),
                        response.posterPath,
                        response.backdropPath
                    )
                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        })

        return movieResults
    }

    override fun getTopRatedMovies(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getTopRatedMovie(object : LoadMoviesCallback {
            override fun onAllMovieReceived(movieResponses: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponses) {
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.overview,
                        convertDateFormat(response.releaseDate),
                        response.voteAverage.toInt(),
                        response.posterPath,
                        response.backdropPath
                    )
                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        })

        return movieResults
    }

    override fun getUpcomingMovies(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getUpcomingMovie(object : LoadMoviesCallback {
            override fun onAllMovieReceived(movieResponses: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponses) {
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.overview,
                        convertDateFormat(response.releaseDate),
                        response.voteAverage.toInt(),
                        response.posterPath,
                        response.backdropPath
                    )
                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        })

        return movieResults
    }

    override fun getOnTheAirTvShows(): LiveData<List<TvShowEntity>> {
        val tvShowResults = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getOnTheAirTvShow(object : LoadTvShowsCallback {
            override fun onAllTvShowReceived(tvShowResponses: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in tvShowResponses) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.name,
                        response.overview,
                        convertDateFormat(response.firstAirDate),
                        response.voteAverage.toInt(),
                        response.posterPath,
                        response.backdropPath
                    )
                    tvShowList.add(tvShow)
                }
                tvShowResults.postValue(tvShowList)
            }
        })

        return tvShowResults
    }

    override fun getPopularTvShows(): LiveData<List<TvShowEntity>> {
        val tvShowResults = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getPopularTvShow(object : LoadTvShowsCallback {
            override fun onAllTvShowReceived(tvShowResponses: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in tvShowResponses) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.name,
                        response.overview,
                        convertDateFormat(response.firstAirDate),
                        response.voteAverage.toInt(),
                        response.posterPath,
                        response.backdropPath
                    )
                    tvShowList.add(tvShow)
                }
                tvShowResults.postValue(tvShowList)
            }
        })

        return tvShowResults
    }

    override fun getTopRatedTvShows(): LiveData<List<TvShowEntity>> {
        val tvShowResults = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getTopRatedTvShow(object : LoadTvShowsCallback {
            override fun onAllTvShowReceived(tvShowResponses: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in tvShowResponses) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.name,
                        response.overview,
                        convertDateFormat(response.firstAirDate),
                        response.voteAverage.toInt(),
                        response.posterPath,
                        response.backdropPath
                    )
                    tvShowList.add(tvShow)
                }
                tvShowResults.postValue(tvShowList)
            }
        })

        return tvShowResults
    }

    override fun getAiringTodayTvShows(): LiveData<List<TvShowEntity>> {
        val tvShowResults = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getAiringTodayTvShow(object : LoadTvShowsCallback {
            override fun onAllTvShowReceived(tvShowResponses: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in tvShowResponses) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.name,
                        response.overview,
                        convertDateFormat(response.firstAirDate),
                        response.voteAverage.toInt(),
                        response.posterPath,
                        response.backdropPath
                    )
                    tvShowList.add(tvShow)
                }
                tvShowResults.postValue(tvShowList)
            }
        })

        return tvShowResults
    }
}