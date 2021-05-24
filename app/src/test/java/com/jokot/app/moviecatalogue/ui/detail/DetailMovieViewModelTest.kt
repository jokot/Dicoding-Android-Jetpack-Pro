package com.jokot.app.moviecatalogue.ui.detail

import com.jokot.app.moviecatalogue.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailFilmViewModelTest {
    private lateinit var viewModel: DetailFilmViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    @Before
    fun setup() {
        viewModel = DetailFilmViewModel()
    }

    @Test
    fun getMovie() {
        viewModel.setSelectedFilm(movieId)
        val movie = viewModel.getMovie()
        assertNotNull(movie)
        assertEquals(dummyMovie.id, movie.id)
        assertEquals(dummyMovie.title, movie.title)
        assertEquals(dummyMovie.overview, movie.overview)
        assertEquals(dummyMovie.releaseDate, movie.releaseDate)
        assertEquals(dummyMovie.score, movie.score)
        assertEquals(dummyMovie.genre, movie.genre)
        assertEquals(dummyMovie.duration, movie.duration)
        assertEquals(dummyMovie.posterPath, movie.posterPath)
        assertEquals(dummyMovie.bannerPath, movie.bannerPath)
    }

    @Test
    fun getMovieWithUnknownId() {
        val movieId = "11"
        viewModel.setSelectedFilm(movieId)
        val exception = assertThrows(UninitializedPropertyAccessException::class.java) {
            viewModel.getMovie()
        }
        assertEquals("lateinit property movie has not been initialized", exception.message)
    }

    @Test
    fun getTvShow() {
        viewModel.setSelectedFilm(tvShowId)
        val tvShow = viewModel.getTvShow()
        assertNotNull(tvShow)
        assertEquals(dummyTvShow.id, tvShow.id)
        assertEquals(dummyTvShow.title, tvShow.title)
        assertEquals(dummyTvShow.overview, tvShow.overview)
        assertEquals(dummyTvShow.releaseDate, tvShow.releaseDate)
        assertEquals(dummyTvShow.score, tvShow.score)
        assertEquals(dummyTvShow.genre, tvShow.genre)
        assertEquals(dummyTvShow.duration, tvShow.duration)
        assertEquals(dummyTvShow.posterPath, tvShow.posterPath)
        assertEquals(dummyTvShow.bannerPath, tvShow.bannerPath)
    }
}