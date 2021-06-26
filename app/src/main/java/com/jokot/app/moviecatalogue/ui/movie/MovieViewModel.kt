package com.jokot.app.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.ImageEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
import com.jokot.app.moviecatalogue.vo.Resource

class MovieViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    fun getConfiguration(): LiveData<Resource<ImageEntity>> = filmRepository.getConfiguration()

    fun getNowPlayingMovies(): LiveData<Resource<PagedList<MovieEntity>>> =
        filmRepository.getNowPlayingMovies()

    fun getPopularMovies(): LiveData<Resource<PagedList<MovieEntity>>> =
        filmRepository.getPopularMovies()

    fun getTopRatedMovies(): LiveData<Resource<PagedList<MovieEntity>>> =
        filmRepository.getTopRatedMovies()

    fun getUpcomingMovies(): LiveData<Resource<PagedList<MovieEntity>>> =
        filmRepository.getUpcomingMovies()
}