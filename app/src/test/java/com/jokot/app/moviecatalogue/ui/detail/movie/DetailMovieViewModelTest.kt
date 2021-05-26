package com.jokot.app.moviecatalogue.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.ImagesEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieDetailEntity
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
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovieDetail()
    private val movieId = dummyMovie.id
    private val dummyConfig = DataDummy.generateDummyConfig()

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observerMovie: Observer<MovieDetailEntity>

    @Mock
    private lateinit var observerImages: Observer<ImagesEntity>

    @Before
    fun setup() {
        viewModel = DetailMovieViewModel(filmRepository)
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
    fun getMovieDetail() {
        val movie = MutableLiveData<MovieDetailEntity>()
        movie.value = dummyMovie

        `when`(filmRepository.getMovieDetail(movieId)).thenReturn(movie)
        viewModel.setSelectedMovie(movieId)
        val movieEntity = viewModel.getMovieDetail().value
        verify(filmRepository).getMovieDetail(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity?.id)
        assertEquals(dummyMovie.title, movieEntity?.title)
        assertEquals(dummyMovie.overview, movieEntity?.overview)
        assertEquals(dummyMovie.releaseDate, movieEntity?.releaseDate)
        assertEquals(dummyMovie.genres, movieEntity?.genres)
        assertEquals(dummyMovie.voteAverage, movieEntity?.voteAverage)
        assertEquals(dummyMovie.posterPath, movieEntity?.posterPath)
        assertEquals(dummyMovie.backdropPath, movieEntity?.backdropPath)

        viewModel.getMovieDetail().observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovie)
    }
}