package com.jokot.app.moviecatalogue.ui.tv

import androidx.lifecycle.ViewModel
import com.jokot.app.moviecatalogue.data.FilmEntity
import com.jokot.app.moviecatalogue.utils.DataDummy

class TvShowViewModel : ViewModel() {
    fun getTvShows(): List<FilmEntity> = DataDummy.generateDummyTvShows()
}