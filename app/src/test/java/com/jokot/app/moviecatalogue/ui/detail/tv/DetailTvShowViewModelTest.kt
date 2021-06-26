package com.jokot.app.moviecatalogue.ui.detail.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.ImageEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity
import com.jokot.app.moviecatalogue.utils.DataDummy
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
class DetailTvShowViewModelTest {
    private lateinit var viewModel: DetailTvShowViewModel
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val tvShowId = dummyTvShow.id
    private val dummyTvShowWithDetail = DataDummy.generateDummyTvShowWithDetail(tvShowId)
    private val dummyConfig = DataDummy.generateDummyConfig()

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observerTvShow: Observer<Resource<TvShowEntity>>

    @Mock
    private lateinit var observerImage: Observer<Resource<ImageEntity>>

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel(filmRepository)
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
    fun getTvShowDetail() {
        val dummyTvShow = Resource.success(dummyTvShowWithDetail)
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyTvShow

        `when`(filmRepository.getTvShowDetail(tvShowId)).thenReturn(tvShow)
        viewModel.setSelectedTvShow(tvShowId)

        viewModel.tvShowDetail.observeForever(observerTvShow)
        verify(observerTvShow).onChanged(dummyTvShow)
    }
}