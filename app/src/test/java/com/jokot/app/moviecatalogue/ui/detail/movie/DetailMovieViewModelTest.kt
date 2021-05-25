package com.jokot.app.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jokot.app.moviecatalogue.data.FilmRepository
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieDetailEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
import com.jokot.app.moviecatalogue.ui.detail.movie.DetailMovieViewModel
import com.jokot.app.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
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

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<MovieDetailEntity>

    @Before
    fun setup() {
        viewModel = DetailMovieViewModel(filmRepository)
    }

    @Test
    fun getMovieDetail() {
        val movieDetail = MutableLiveData<MovieDetailEntity>()
        movieDetail.value = dummyMovie

        `when`(filmRepository.getMovieDetail(movieId)).thenReturn(movieDetail)
        viewModel.setSelectedMovie(movieId)
        val movie = viewModel.getMovieDetail().value
        assertNotNull(movie)
        assertEquals(dummyMovie.id, movie?.id)
        assertEquals(dummyMovie.title, movie?.title)
        assertEquals(dummyMovie.overview, movie?.overview)
        assertEquals(dummyMovie.releaseDate, movie?.releaseDate)
        assertEquals(dummyMovie.voteAverage, movie?.voteAverage)
        assertEquals(dummyMovie.posterPath, movie?.posterPath)
        assertEquals(dummyMovie.backdropPath, movie?.backdropPath)

        viewModel.getMovieDetail().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

//    @Test
//    fun getMovieWithUnknownId() {
//        val movieId = 11
//        viewModel.setSelectedMovie(movieId)
//        val exception = assertThrows(UninitializedPropertyAccessException::class.java) {
//            viewModel.getMovieDetail()
//        }
//        assertEquals("lateinit property movie has not been initialized", exception.message)
//    }
}