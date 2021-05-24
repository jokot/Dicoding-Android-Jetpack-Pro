package com.jokot.app.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jokot.app.moviecatalogue.data.source.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieDetailEntity

class DetailMovieViewModel(private val filmRepository: FilmRepository) :
    ViewModel() {
    private lateinit var movieId: String

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun getMovieDetail(): LiveData<MovieDetailEntity> = filmRepository.getMovieDetail(movieId)

}