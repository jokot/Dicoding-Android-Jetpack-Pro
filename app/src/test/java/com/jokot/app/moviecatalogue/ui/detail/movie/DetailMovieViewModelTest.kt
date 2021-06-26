package com.jokot.app.moviecatalogue.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.ImageEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
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
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.movieId
    private val dummyMovieWithDetail = DataDummy.generateDummyMovieWithDetail(movieId)
    private val dummyConfig = DataDummy.generateDummyConfig()

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observerMovie: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var observerImage: Observer<Resource<ImageEntity>>

    @Before
    fun setup() {
        viewModel = DetailMovieViewModel(filmRepository)
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
    fun getMovieDetail() {
        val dummyMovie = Resource.success(dummyMovieWithDetail)
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyMovie

        `when`(filmRepository.getMovieDetail(movieId)).thenReturn(movie)
        viewModel.setSelectedMovie(movieId)

        viewModel.movieDetail.observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovie)
    }
}