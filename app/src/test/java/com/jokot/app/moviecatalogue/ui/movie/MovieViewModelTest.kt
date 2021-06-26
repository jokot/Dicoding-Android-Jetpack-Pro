package com.jokot.app.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.ImageEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
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
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel
    private val dummyConfig = DataDummy.generateDummyConfig()
    private val dummyMovies = DataDummy.generateDummyMovies()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observerMovies: Observer<Resource<List<MovieEntity>>>

    @Mock
    private lateinit var observerImage: Observer<Resource<ImageEntity>>


    @Before
    fun setup() {
        viewModel = MovieViewModel(filmRepository)
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
    fun getNowPlayingMovies() {
        val dummyMovies = Resource.success(dummyMovies)
        val movies = MutableLiveData<Resource<List<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(filmRepository.getNowPlayingMovies()).thenReturn(movies)
        val movieEntities = viewModel.getNowPlayingMovies().value?.data
        verify(filmRepository).getNowPlayingMovies()
        assertNotNull(movieEntities)
        assertEquals(dummyMovies.data?.size, movieEntities?.size)

        viewModel.getNowPlayingMovies().observeForever(observerMovies)
        verify(observerMovies).onChanged(dummyMovies)
    }

    @Test
    fun getPopularMovies() {
        val dummyMovies = Resource.success(dummyMovies)
        val movies = MutableLiveData<Resource<List<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(filmRepository.getPopularMovies()).thenReturn(movies)
        val moviesEntities = viewModel.getPopularMovies().value?.data
        verify(filmRepository).getPopularMovies()
        assertNotNull(moviesEntities)
        assertEquals(dummyMovies.data?.size, moviesEntities?.size)

        viewModel.getPopularMovies().observeForever(observerMovies)
        verify(observerMovies).onChanged(dummyMovies)
    }

    @Test
    fun getTopRatedMovies() {
        val dummyMovies = Resource.success(dummyMovies)
        val movies = MutableLiveData<Resource<List<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(filmRepository.getTopRatedMovies()).thenReturn(movies)
        val moviesEntities = viewModel.getTopRatedMovies().value?.data
        verify(filmRepository).getTopRatedMovies()
        assertNotNull(moviesEntities)
        assertEquals(dummyMovies.data?.size, moviesEntities?.size)

        viewModel.getTopRatedMovies().observeForever(observerMovies)
        verify(observerMovies).onChanged(dummyMovies)
    }

    @Test
    fun getUpcomingMovies() {
        val dummyMovies = Resource.success(dummyMovies)
        val movies = MutableLiveData<Resource<List<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(filmRepository.getUpcomingMovies()).thenReturn(movies)
        val moviesEntities = viewModel.getUpcomingMovies().value?.data
        verify(filmRepository).getUpcomingMovies()
        assertNotNull(moviesEntities)
        assertEquals(dummyMovies.data?.size, moviesEntities?.size)

        viewModel.getUpcomingMovies().observeForever(observerMovies)
        verify(observerMovies).onChanged(dummyMovies)
    }
}