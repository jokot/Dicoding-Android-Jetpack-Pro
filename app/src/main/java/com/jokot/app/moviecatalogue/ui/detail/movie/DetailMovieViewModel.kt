package com.jokot.app.moviecatalogue.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.ImagesEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieDetailEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
import com.jokot.app.moviecatalogue.vo.Resource

class DetailMovieViewModel(private val filmRepository: FilmRepository) :
    ViewModel() {
    val movieId = MutableLiveData<Int>()

    fun setSelectedMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    fun getConfiguration(): LiveData<ImagesEntity> = filmRepository.getConfiguration()

    var movieDetail: LiveData<Resource<MovieDetailEntity>> =
        Transformations.switchMap(movieId) { mMovieId ->
            filmRepository.getMovieDetail(mMovieId)
        }

    fun setBookmark() {
        val movieDetailResource = movieDetail.value
        if (movieDetailResource != null) {
            val movieDetailEntity = movieDetailResource.data

            movieDetailEntity?.let {
                val movieEntity = MovieEntity(
                    it.id,
                    it.title,
                    StringBuilder(it.overview).toString(),
                    it.releaseDate, it.voteAverage, it.posterPath, it.backdropPath,
                )

                val newState = !it.bookmarked

                filmRepository.setMovieBookmark(movieEntity, newState)
                filmRepository.setMovieDetailBookmark(it, newState)
            }
        }
    }
}