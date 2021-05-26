package com.jokot.app.moviecatalogue.ui.detail.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.ImagesEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowDetailEntity
import kotlin.properties.Delegates

class DetailTvShowViewModel(private val filmRepository: FilmRepository) : ViewModel() {
    private var tvShowId by Delegates.notNull<Int>()

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId = tvShowId
    }

    fun getConfiguration(): LiveData<ImagesEntity> = filmRepository.getConfiguration()

    fun getTvShowDetail(): LiveData<TvShowDetailEntity> = filmRepository.getTvShowDetail(tvShowId)
}