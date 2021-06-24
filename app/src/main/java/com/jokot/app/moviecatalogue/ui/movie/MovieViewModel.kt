package com.jokot.app.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.ImagesEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
import com.jokot.app.moviecatalogue.utils.Event
import com.jokot.app.moviecatalogue.vo.Resource

class MovieViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    fun getConfiguration(): LiveData<ImagesEntity> = filmRepository.getConfiguration()

    fun getInitData(): Event<LiveData<Resource<List<MovieEntity>>>> =
        Event(filmRepository.getNowPlayingMovies())

    fun getNowPlayingMovies(): LiveData<Resource<List<MovieEntity>>> =
        filmRepository.getNowPlayingMovies()

    fun getPopularMovies(): LiveData<Resource<List<MovieEntity>>> =
        filmRepository.getPopularMovies()

    fun getTopRatedMovies(): LiveData<Resource<List<MovieEntity>>> =
        filmRepository.getTopRatedMovies()

    fun getUpcomingMovies(): LiveData<Resource<List<MovieEntity>>> =
        filmRepository.getUpcomingMovies()
}