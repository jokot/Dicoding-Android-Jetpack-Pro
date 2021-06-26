package com.jokot.app.moviecatalogue.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.ImageEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity
import com.jokot.app.moviecatalogue.utils.DataDummy
import com.jokot.app.moviecatalogue.utils.Event
import com.jokot.app.moviecatalogue.vo.Resource
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
    private lateinit var observerTvShows: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var observerImage: Observer<Resource<ImageEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setup() {
        viewModel = TvShowViewModel(filmRepository)
    }

    @Test
    fun getConfiguration() {
        val dummyConfig = Resource.success(dummyConfig)
        val images = MutableLiveData<Resource<ImageEntity>>()
        images.value = dummyConfig

        `when`(filmRepository.getConfiguration()).thenReturn(images)
        val imagesEntity = viewModel.getConfiguration().value?.data
        verify(filmRepository).getConfiguration()
        assertNotNull(imagesEntity)
        assertEquals(dummyConfig.data?.posterSize, imagesEntity?.posterSize)

        viewModel.getConfiguration().observeForever(observerImage)
        verify(observerImage).onChanged(dummyConfig)
    }

    @Test
    fun getOnTheAirTvShow() {
        val dummyTvShows = Resource.success(pagedList)
        `when`(dummyTvShows.data?.size).thenReturn(this.dummyTvShows.size)
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = dummyTvShows

        `when`(filmRepository.getOnTheAirTvShows()).thenReturn(tvShows)
        val tvShowsEntities = viewModel.getOnTheAirTvShow().value?.data
        verify(filmRepository).getOnTheAirTvShows()
        assertNotNull(tvShowsEntities)
        assertEquals(dummyTvShows.data?.size, tvShowsEntities?.size)

        viewModel.getOnTheAirTvShow().observeForever(observerTvShows)
        verify(observerTvShows).onChanged(dummyTvShows)
    }

    @Test
    fun getPopularTvShow() {
        val dummyTvShows = Resource.success(pagedList)
        `when`(dummyTvShows.data?.size).thenReturn(this.dummyTvShows.size)
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = dummyTvShows

        `when`(filmRepository.getPopularTvShows()).thenReturn(tvShows)
        val tvShowsEntities = viewModel.getPopularTvShow().value?.data
        verify(filmRepository).getPopularTvShows()
        assertNotNull(tvShowsEntities)
        assertEquals(dummyTvShows.data?.size, tvShowsEntities?.size)

        viewModel.getPopularTvShow().observeForever(observerTvShows)
        verify(observerTvShows).onChanged(dummyTvShows)
    }

    @Test
    fun getTopRatedTvShow() {
        val dummyTvShows = Resource.success(pagedList)
        `when`(dummyTvShows.data?.size).thenReturn(this.dummyTvShows.size)
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = dummyTvShows

        `when`(filmRepository.getTopRatedTvShows()).thenReturn(tvShows)
        val tvShowsEntities = viewModel.getTopRatedTvShow().value?.data
        verify(filmRepository).getTopRatedTvShows()
        assertNotNull(tvShowsEntities)
        assertEquals(dummyTvShows.data?.size, tvShowsEntities?.size)

        viewModel.getTopRatedTvShow().observeForever(observerTvShows)
        verify(observerTvShows).onChanged(dummyTvShows)
    }

    @Test
    fun getAiringTodayTvShow() {
        val dummyTvShows = Resource.success(pagedList)
        `when`(dummyTvShows.data?.size).thenReturn(this.dummyTvShows.size)
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = dummyTvShows

        `when`(filmRepository.getAiringTodayTvShows()).thenReturn(tvShows)
        val tvShowsEntities = viewModel.getAiringTodayTvShow().value?.data
        verify(filmRepository).getAiringTodayTvShows()
        assertNotNull(tvShowsEntities)
        assertEquals(dummyTvShows.data?.size, tvShowsEntities?.size)

        viewModel.getAiringTodayTvShow().observeForever(observerTvShows)
        verify(observerTvShows).onChanged(dummyTvShows)
    }
}