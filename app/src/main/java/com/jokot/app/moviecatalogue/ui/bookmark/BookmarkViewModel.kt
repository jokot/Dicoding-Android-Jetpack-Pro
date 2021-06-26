package com.jokot.app.moviecatalogue.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.ImagesEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity
import com.jokot.app.moviecatalogue.utils.Event

class BookmarkViewModel(private val filmRepository: FilmRepository): ViewModel() {

    fun getInitData(): Event<LiveData<List<MovieEntity>>> =
        Event(filmRepository.getFavoriteMovie())

    fun getConfiguration(): LiveData<ImagesEntity> = filmRepository.getConfiguration()

    fun getBookmarkMovies(): LiveData<List<MovieEntity>> =
        filmRepository.getFavoriteMovie()

    fun getBookmarkTvShows(): LiveData<List<TvShowEntity>> =
        filmRepository.getFavoriteTvShow()
}