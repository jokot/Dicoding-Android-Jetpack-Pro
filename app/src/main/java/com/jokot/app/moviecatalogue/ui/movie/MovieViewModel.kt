package com.jokot.app.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.jokot.app.moviecatalogue.data.FilmEntity
import com.jokot.app.moviecatalogue.utils.DataDummy

class MovieViewModel : ViewModel() {
    fun getMovies(): List<FilmEntity> = DataDummy.generateDummyMovies()
}