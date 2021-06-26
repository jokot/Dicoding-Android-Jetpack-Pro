package com.jokot.app.moviecatalogue.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.ImagesEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity
import com.jokot.app.moviecatalogue.vo.Resource

class TvShowViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    fun getConfiguration(): LiveData<ImagesEntity> = filmRepository.getConfiguration()

    fun getOnTheAirTvShow(): LiveData<Resource<List<TvShowEntity>>> =
        filmRepository.getOnTheAirTvShows()

    fun getPopularTvShow(): LiveData<Resource<List<TvShowEntity>>> =
        filmRepository.getPopularTvShows()

    fun getTopRatedTvShow(): LiveData<Resource<List<TvShowEntity>>> =
        filmRepository.getTopRatedTvShows()

    fun getAiringTodayTvShow(): LiveData<Resource<List<TvShowEntity>>> =
        filmRepository.getAiringTodayTvShows()
}