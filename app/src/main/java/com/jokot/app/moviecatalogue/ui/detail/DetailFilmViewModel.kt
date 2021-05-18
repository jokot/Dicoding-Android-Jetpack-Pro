package com.jokot.app.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.jokot.app.moviecatalogue.data.FilmEntity
import com.jokot.app.moviecatalogue.utils.DataDummy

class DetailFilmViewModel : ViewModel() {
    private lateinit var filmId: String

    fun setSelectedFilm(filmId: String) {
        this.filmId = filmId
    }

    fun getMovie(): FilmEntity {
        lateinit var movie: FilmEntity
        val moviesEntities = DataDummy.generateDummyMovies()
        for (movieEntity in moviesEntities) {
            if (movieEntity.id == filmId) {
                movie = movieEntity
                break
            }
        }
        return movie
    }

    fun getTvShow(): FilmEntity {
        lateinit var tvShow: FilmEntity
        val tvShowsEntities = DataDummy.generateDummyTvShows()
        for (tvShowEntity in tvShowsEntities) {
            if (tvShowEntity.id == filmId) {
                tvShow = tvShowEntity
                break
            }
        }
        return tvShow
    }
}