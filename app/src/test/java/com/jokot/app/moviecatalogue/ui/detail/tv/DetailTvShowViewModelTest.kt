package com.jokot.app.moviecatalogue.ui.detail.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.ImagesEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowDetailEntity
import com.jokot.app.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvShowViewModelTest {
    private lateinit var viewModel: DetailTvShowViewModel
    private val dummyTvShow = DataDummy.generateDummyTvShowDetail()
    private val tvShowId = dummyTvShow.id
    private val dummyConfig = DataDummy.generateDummyConfig()

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observerTvShow: Observer<TvShowDetailEntity>

    @Mock
    private lateinit var observerImages: Observer<ImagesEntity>

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel(filmRepository)
    }

    @Test
    fun getConfiguration() {
        val images = MutableLiveData<ImagesEntity>()
        images.value = dummyConfig

        `when`(filmRepository.getConfiguration()).thenReturn(images)
        val imagesEntity = viewModel.getConfiguration().value
        verify(filmRepository).getConfiguration()
        assertNotNull(imagesEntity)
        assertEquals(dummyConfig.posterSizes.size, imagesEntity?.posterSizes?.size)

        viewModel.getConfiguration().observeForever(observerImages)
        verify(observerImages).onChanged(dummyConfig)
    }

    @Test
    fun getTvShowDetail() {
        val tvShow = MutableLiveData<TvShowDetailEntity>()
        tvShow.value = dummyTvShow

        `when`(filmRepository.getTvShowDetail(tvShowId)).thenReturn(tvShow)
        viewModel.setSelectedTvShow(tvShowId)
        val tvShowEntity = viewModel.getTvShowDetail().value
        verify(filmRepository).getTvShowDetail(tvShowId)
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.id, tvShowEntity?.id)
        assertEquals(dummyTvShow.name, tvShowEntity?.name)
        assertEquals(dummyTvShow.overview, tvShowEntity?.overview)
        assertEquals(dummyTvShow.firstAirDate, tvShowEntity?.firstAirDate)
        assertEquals(dummyTvShow.genres, tvShowEntity?.genres)
        assertEquals(dummyTvShow.voteAverage, tvShowEntity?.voteAverage)
        assertEquals(dummyTvShow.posterPath, tvShowEntity?.posterPath)
        assertEquals(dummyTvShow.backdropPath, tvShowEntity?.backdropPath)

        viewModel.getTvShowDetail().observeForever(observerTvShow)
        verify(observerTvShow).onChanged(dummyTvShow)
    }
}