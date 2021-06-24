package com.jokot.app.moviecatalogue.ui.detail.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.ImagesEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowDetailEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity
import com.jokot.app.moviecatalogue.vo.Resource

class DetailTvShowViewModel(private val filmRepository: FilmRepository) : ViewModel() {
    val tvShowId = MutableLiveData<Int>()

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId.value = tvShowId
    }

    fun getConfiguration(): LiveData<ImagesEntity> = filmRepository.getConfiguration()

    var tvShowDetail: LiveData<Resource<TvShowDetailEntity>> =
        Transformations.switchMap(tvShowId) { mTvShowId ->
            filmRepository.getTvShowDetail(mTvShowId)
        }

    fun setBookmark() {
        val tvShowDetailResource = tvShowDetail.value

        if (tvShowDetailResource != null) {
            val tvShowDetailEntity = tvShowDetailResource.data

            tvShowDetailEntity?.let {
                val tvShowEntity = TvShowEntity(
                    it.id,
                    it.name,
                    it.overview,
                    it.firstAirDate,
                    it.voteAverage,
                    it.posterPath,
                    it.backdropPath,
                    it.bookmarked
                )
                val newState = !it.bookmarked
                filmRepository.setTvShowBookmark(tvShowEntity, newState)
                filmRepository.setTvShowDetailBookmark(it, newState)
            }
        }
    }
}