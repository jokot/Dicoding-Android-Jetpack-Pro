package com.jokot.app.moviecatalogue.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.ImageEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
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
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel
    private val dummyConfig = DataDummy.generateDummyConfig()
    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyTvShows = DataDummy.generateDummyTvShows()


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observerMovies: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var observerTvShows: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observerImage: Observer<Resource<ImageEntity>>

    @Mock
    private lateinit var pagedListMovie: PagedList<MovieEntity>

    @Mock
    private lateinit var pagedListTvShow: PagedList<TvShowEntity>


            @Before
    fun setup() {
        viewModel = FavoriteViewModel(filmRepository)
    }

    @Test
    fun getConfiguration() {
        val dummyConfig = Resource.success(dummyConfig)
        val images = MutableLiveData<Resource<ImageEntity>>()
        images.value = dummyConfig

        Mockito.`when`(filmRepository.getConfiguration()).thenReturn(images)
        val imagesEntity = viewModel.getConfiguration().value?.data
        verify(filmRepository).getConfiguration()
        assertNotNull(imagesEntity)
        assertEquals(dummyConfig.data?.posterSize, imagesEntity?.posterSize)

        viewModel.getConfiguration().observeForever(observerImage)
        verify(observerImage).onChanged(dummyConfig)
    }

    @Test
    fun getFavoriteMovies() {
        val dummyMovies = pagedListMovie
        `when`(dummyMovies.size).thenReturn(this.dummyMovies.size)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovies

        `when`(filmRepository.getFavoriteMovie()).thenReturn(movies)
        val moviesEntities = viewModel.getFavoriteMovies().value
        verify(filmRepository).getFavoriteMovie()
        assertNotNull(moviesEntities)
        assertEquals(dummyMovies.size, moviesEntities?.size)

        viewModel.getFavoriteMovies().observeForever(observerMovies)
        verify(observerMovies).onChanged(dummyMovies)
    }

    @Test
    fun getFavoriteTvShows() {
        val dummyTvShows = pagedListTvShow
        `when`(dummyTvShows.size).thenReturn(this.dummyTvShows.size)
        val tvShows = MutableLiveData<PagedList<TvShowEntity>>()
        tvShows.value = dummyTvShows

        `when`(filmRepository.getFavoriteTvShow()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getFavoriteTvShows().value
        verify(filmRepository).getFavoriteTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(dummyMovies.size, tvShowEntities?.size)

        viewModel.getFavoriteTvShows().observeForever(observerTvShows)
        verify(observerTvShows).onChanged(dummyTvShows)
    }
}