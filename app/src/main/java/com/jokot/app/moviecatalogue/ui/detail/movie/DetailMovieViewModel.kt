package com.jokot.app.moviecatalogue.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.ImagesEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieDetailEntity
import kotlin.properties.Delegates

class DetailMovieViewModel(private val filmRepository: FilmRepository) :
    ViewModel() {
    private var movieId by Delegates.notNull<Int>()

    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun getConfiguration(): LiveData<ImagesEntity> = filmRepository.getConfiguration()

    fun getMovieDetail(): LiveData<MovieDetailEntity> = filmRepository.getMovieDetail(movieId)
}