package com.jokot.app.moviecatalogue.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.ImagesEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity
import com.jokot.app.moviecatalogue.utils.Event

class TvShowViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    fun getConfiguration(): LiveData<ImagesEntity> = filmRepository.getConfiguration()

    fun getInitData(): Event<LiveData<List<TvShowEntity>>> =
        Event(filmRepository.getOnTheAirTvShows())

    fun getOnTheAirTvShow(): LiveData<List<TvShowEntity>> = filmRepository.getOnTheAirTvShows()

    fun getPopularTvShow(): LiveData<List<TvShowEntity>> = filmRepository.getPopularTvShows()

    fun getTopRatedTvShow(): LiveData<List<TvShowEntity>> = filmRepository.getTopRatedTvShows()

    fun getAiringTodayTvShow(): LiveData<List<TvShowEntity>> =
        filmRepository.getAiringTodayTvShows()
}