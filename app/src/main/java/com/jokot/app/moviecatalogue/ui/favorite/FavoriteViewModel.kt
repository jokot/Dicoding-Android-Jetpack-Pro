package com.jokot.app.moviecatalogue.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.ImagesEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity

class FavoriteViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    fun getConfiguration(): LiveData<ImagesEntity> = filmRepository.getConfiguration()

    fun getFavoriteMovies(): LiveData<List<MovieEntity>> =
        filmRepository.getFavoriteMovie()

    fun getFavoriteTvShows(): LiveData<List<TvShowEntity>> =
        filmRepository.getFavoriteTvShow()
}