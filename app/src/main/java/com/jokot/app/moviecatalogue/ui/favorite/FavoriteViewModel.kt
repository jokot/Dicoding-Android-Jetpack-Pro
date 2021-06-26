package com.jokot.app.moviecatalogue.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.ImageEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity
import com.jokot.app.moviecatalogue.vo.Resource

class FavoriteViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    fun getConfiguration(): LiveData<Resource<ImageEntity>> = filmRepository.getConfiguration()

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> =
        filmRepository.getFavoriteMovie()

    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> =
        filmRepository.getFavoriteTvShow()

    fun setFavoriteMovie(movieEntity: MovieEntity) {
        val newState = !movieEntity.favorite
        filmRepository.setMovieFavorite(movieEntity,newState)
    }

    fun setFavoriteTvShow(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.favorite
        filmRepository.setTvShowFavorite(tvShowEntity,newState)
    }
}