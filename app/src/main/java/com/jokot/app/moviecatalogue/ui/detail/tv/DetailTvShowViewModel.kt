package com.jokot.app.moviecatalogue.ui.detail.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.ImagesEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity
import com.jokot.app.moviecatalogue.vo.Resource

class DetailTvShowViewModel(private val filmRepository: FilmRepository) : ViewModel() {
    val tvShowId = MutableLiveData<Int>()

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId.value = tvShowId
    }

    fun getConfiguration(): LiveData<ImagesEntity> = filmRepository.getConfiguration()

    var tvShowDetail: LiveData<Resource<TvShowEntity>> =
        Transformations.switchMap(tvShowId) { mTvShowId ->
            filmRepository.getTvShowDetail(mTvShowId)
        }

    fun setFavorite() {
        val tvShowDetailResource = tvShowDetail.value

        if (tvShowDetailResource != null) {
            val tvShowDetailEntity = tvShowDetailResource.data

            tvShowDetailEntity?.let {

                val newState = !it.favorite
                filmRepository.setTvShowFavorite(it, newState)
            }
        }
    }
}