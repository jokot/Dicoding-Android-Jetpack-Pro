package com.jokot.app.moviecatalogue.ui.tv

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setup() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShows() {
        val tvShowsEntities = viewModel.getTvShows()
        assertNotNull(tvShowsEntities)
        assertEquals(10, tvShowsEntities.size)
    }
}