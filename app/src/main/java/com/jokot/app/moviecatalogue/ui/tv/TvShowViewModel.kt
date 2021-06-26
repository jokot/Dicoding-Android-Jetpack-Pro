package com.jokot.app.moviecatalogue.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.ImageEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity
import com.jokot.app.moviecatalogue.vo.Resource

class TvShowViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    fun getConfiguration(): LiveData<Resource<ImageEntity>> = filmRepository.getConfiguration()

    fun getOnTheAirTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> =
        filmRepository.getOnTheAirTvShows()

    fun getPopularTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> =
        filmRepository.getPopularTvShows()

    fun getTopRatedTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> =
        filmRepository.getTopRatedTvShows()

    fun getAiringTodayTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> =
        filmRepository.getAiringTodayTvShows()
}