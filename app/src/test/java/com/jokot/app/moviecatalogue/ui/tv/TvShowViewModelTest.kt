package com.jokot.app.moviecatalogue.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.ImagesEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity
import com.jokot.app.moviecatalogue.utils.DataDummy
import com.jokot.app.moviecatalogue.utils.Event
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
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel
    private val dummyConfig = DataDummy.generateDummyConfig()
    private val dummyTvShows = DataDummy.generateDummyTvShows()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observerTvShows: Observer<List<TvShowEntity>>

    @Mock
    private lateinit var observerImages: Observer<ImagesEntity>

    @Before
    fun setup() {
        viewModel = TvShowViewModel(filmRepository)
    }

    @Test
    fun getConfiguration() {
        val images = MutableLiveData<ImagesEntity>()
        images.value = dummyConfig

        `when`(filmRepository.getConfiguration()).thenReturn(images)
        val imageEntity = viewModel.getConfiguration().value
        verify(filmRepository).getConfiguration()
        assertNotNull(imageEntity)
        assertEquals(dummyConfig.posterSizes.size, imageEntity?.posterSizes?.size)

        viewModel.getConfiguration().observeForever(observerImages)
        verify(observerImages).onChanged(dummyConfig)
    }

    @Test
    fun getInitData() {
        val tvShows = Event(MutableLiveData<List<TvShowEntity>>())
        tvShows.peekContent().value = dummyTvShows

        `when`(filmRepository.getOnTheAirTvShows()).thenReturn(tvShows.peekContent())
        val tvShowsEntities = viewModel.getOnTheAirTvShow().value
        verify(filmRepository).getOnTheAirTvShows()
        assertNotNull(tvShowsEntities)
        assertEquals(dummyTvShows.size, tvShowsEntities?.size)

        viewModel.getInitData().peekContent().observeForever(observerTvShows)
        verify(observerTvShows).onChanged(dummyTvShows)
    }

    @Test
    fun getOnTheAirTvShow() {
        val tvShows = MutableLiveData<List<TvShowEntity>>()
        tvShows.value = dummyTvShows

        `when`(filmRepository.getOnTheAirTvShows()).thenReturn(tvShows)
        val tvShowsEntities = viewModel.getOnTheAirTvShow().value
        verify(filmRepository).getOnTheAirTvShows()
        assertNotNull(tvShowsEntities)
        assertEquals(dummyTvShows.size, tvShowsEntities?.size)

        viewModel.getOnTheAirTvShow().observeForever(observerTvShows)
        verify(observerTvShows).onChanged(dummyTvShows)
    }

    @Test
    fun getPopularTvShow() {
        val tvShows = MutableLiveData<List<TvShowEntity>>()
        tvShows.value = dummyTvShows

        `when`(filmRepository.getPopularTvShows()).thenReturn(tvShows)
        val tvShowsEntities = viewModel.getPopularTvShow().value
        verify(filmRepository).getPopularTvShows()
        assertNotNull(tvShowsEntities)
        assertEquals(dummyTvShows.size, tvShowsEntities?.size)

        viewModel.getPopularTvShow().observeForever(observerTvShows)
        verify(observerTvShows).onChanged(dummyTvShows)
    }

    @Test
    fun getTopRatedTvShow() {
        val tvShows = MutableLiveData<List<TvShowEntity>>()
        tvShows.value = dummyTvShows

        `when`(filmRepository.getTopRatedTvShows()).thenReturn(tvShows)
        val tvShowsEntities = viewModel.getTopRatedTvShow().value
        verify(filmRepository).getTopRatedTvShows()
        assertNotNull(tvShowsEntities)
        assertEquals(dummyTvShows.size, tvShowsEntities?.size)

        viewModel.getTopRatedTvShow().observeForever(observerTvShows)
        verify(observerTvShows).onChanged(dummyTvShows)
    }

    @Test
    fun getAiringTodayTvShow() {
        val tvShows = MutableLiveData<List<TvShowEntity>>()
        tvShows.value = dummyTvShows

        `when`(filmRepository.getAiringTodayTvShows()).thenReturn(tvShows)
        val tvShowsEntities = viewModel.getAiringTodayTvShow().value
        verify(filmRepository).getAiringTodayTvShows()
        assertNotNull(tvShowsEntities)
        assertEquals(dummyTvShows.size, tvShowsEntities?.size)

        viewModel.getAiringTodayTvShow().observeForever(observerTvShows)
        verify(observerTvShows).onChanged(dummyTvShows)
    }
}