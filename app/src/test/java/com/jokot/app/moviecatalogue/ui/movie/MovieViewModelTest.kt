package com.jokot.app.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.ImagesEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
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
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel
    private val dummyConfig = DataDummy.generateDummyConfig()
    private val dummyMovies = DataDummy.generateDummyMovies()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observerMovies: Observer<List<MovieEntity>>

    @Mock
    private lateinit var observerImages: Observer<ImagesEntity>


    @Before
    fun setup() {
        viewModel = MovieViewModel(filmRepository)
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
    fun getInitData() {
        val movies = Event(MutableLiveData<List<MovieEntity>>())
        movies.peekContent().value = dummyMovies

        `when`(filmRepository.getNowPlayingMovies()).thenReturn(movies.peekContent())
        val movieEntities = viewModel.getInitData().peekContent().value
        verify(filmRepository).getNowPlayingMovies()
        assertNotNull(movieEntities)
        assertEquals(dummyMovies.size, movieEntities?.size)

        viewModel.getInitData().peekContent().observeForever(observerMovies)
        verify(observerMovies).onChanged(dummyMovies)
    }

    @Test
    fun getNowPlayingMovies() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(filmRepository.getNowPlayingMovies()).thenReturn(movies)
        val movieEntities = viewModel.getNowPlayingMovies().value
        verify(filmRepository).getNowPlayingMovies()
        assertNotNull(movieEntities)
        assertEquals(dummyMovies.size, movieEntities?.size)

        viewModel.getNowPlayingMovies().observeForever(observerMovies)
        verify(observerMovies).onChanged(dummyMovies)
    }

    @Test
    fun getPopularMovies() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(filmRepository.getPopularMovies()).thenReturn(movies)
        val moviesEntities = viewModel.getPopularMovies().value
        verify(filmRepository).getPopularMovies()
        assertNotNull(moviesEntities)
        assertEquals(dummyMovies.size, moviesEntities?.size)

        viewModel.getPopularMovies().observeForever(observerMovies)
        verify(observerMovies).onChanged(dummyMovies)
    }

    @Test
    fun getTopRatedMovies() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(filmRepository.getTopRatedMovies()).thenReturn(movies)
        val moviesEntities = viewModel.getTopRatedMovies().value
        verify(filmRepository).getTopRatedMovies()
        assertNotNull(moviesEntities)
        assertEquals(dummyMovies.size, moviesEntities?.size)

        viewModel.getTopRatedMovies().observeForever(observerMovies)
        verify(observerMovies).onChanged(dummyMovies)
    }

    @Test
    fun getUpcomingMovies() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(filmRepository.getUpcomingMovies()).thenReturn(movies)
        val moviesEntities = viewModel.getUpcomingMovies().value
        verify(filmRepository).getUpcomingMovies()
        assertNotNull(moviesEntities)
        assertEquals(dummyMovies.size, moviesEntities?.size)

        viewModel.getUpcomingMovies().observeForever(observerMovies)
        verify(observerMovies).onChanged(dummyMovies)
    }
}