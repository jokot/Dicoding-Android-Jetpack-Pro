package com.jokot.app.moviecatalogue.data


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jokot.app.moviecatalogue.data.source.remote.RemoteDataSource
import com.jokot.app.moviecatalogue.utils.DataDummy
import com.jokot.app.moviecatalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class FilmRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val filmRepository = FakeFilmRepository(remote)

    private val movieResponses = DataDummy.generateRemoteDummyMovie()
    private val movieId = movieResponses[0].id
    private val tvShowResponses = DataDummy.generateRemoteDummyTvShow()
    private val tvShowId = tvShowResponses[0].id
    private val imagesResponse = DataDummy.generateRemoteDummyConfig()

    private val movieDetailResponse = DataDummy.generateRemoteDummyMovieDetail()
    private val tvShowDetailResponse = DataDummy.generateRemoteDummyTvShowDetail()

    @Test
    fun getConfiguration() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadConfigurationCallback)
                .onImagesConfigurationReceived(imagesResponse)
            null
        }.`when`(remote).getConfiguration(any())

        val imagesEntity = LiveDataTestUtil.getValue(filmRepository.getConfiguration())

        verify(remote).getConfiguration(any())
        assertNotNull(imagesEntity)
        assertEquals(imagesResponse.posterSizes.size, imagesEntity.posterSizes.size)
    }

    @Test
    fun getMovieDetail() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadMovieDetailCallback)
                .onMovieDetailReceived(movieDetailResponse)
            null
        }.`when`(remote).getMovieDetail(eq(movieId), any())
        val movieDetailEntity = LiveDataTestUtil.getValue(filmRepository.getMovieDetail(movieId))

        verify(remote).getMovieDetail(eq(movieId), any())

        assertNotNull(movieDetailEntity)
        assertNotNull(movieDetailEntity.title)
        assertEquals(movieDetailResponse.title, movieDetailEntity.title)
    }

    @Test
    fun getTvShowDetail() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadTvShowDetailCallback)
                .onTvShowDetailReceived(tvShowDetailResponse)
            null
        }.`when`(remote).getTvShowDetail(eq(tvShowId), any())

        val tvShowDetailEntity = LiveDataTestUtil.getValue(filmRepository.getTvShowDetail(tvShowId))
        verify(remote).getTvShowDetail(eq(tvShowId), any())

        assertNotNull(tvShowDetailEntity)
        assertNotNull(tvShowDetailEntity.name)
        assertEquals(tvShowDetailResponse.name, tvShowDetailEntity.name)
    }

    @Test
    fun getNowPlayingMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMovieReceived(movieResponses)
            null
        }.`when`(remote).getNowPlayingMovie(any())
        val movieEntities = LiveDataTestUtil.getValue(filmRepository.getNowPlayingMovies())
        verify(remote).getNowPlayingMovie(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size, movieEntities.size)
    }

    @Test
    fun getPopularMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMovieReceived(movieResponses)
            null
        }.`when`(remote).getPopularMovie(any())
        val movieEntities = LiveDataTestUtil.getValue(filmRepository.getPopularMovies())
        verify(remote).getPopularMovie(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size, movieEntities.size)
    }

    @Test
    fun getTopRatedMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMovieReceived(movieResponses)
            null
        }.`when`(remote).getTopRatedMovie(any())
        val movieEntities = LiveDataTestUtil.getValue(filmRepository.getTopRatedMovies())
        verify(remote).getTopRatedMovie(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size, movieEntities.size)
    }

    @Test
    fun getUpcomingMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMovieReceived(movieResponses)
            null
        }.`when`(remote).getUpcomingMovie(any())
        val movieEntities = LiveDataTestUtil.getValue(filmRepository.getUpcomingMovies())
        verify(remote).getUpcomingMovie(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size, movieEntities.size)
    }

    @Test
    fun getOnTheAirTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback)
                .onAllTvShowReceived(tvShowResponses)
            null
        }.`when`(remote).getOnTheAirTvShow(any())
        val tvShowEntities = LiveDataTestUtil.getValue(filmRepository.getOnTheAirTvShows())
        verify(remote).getOnTheAirTvShow(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size, tvShowEntities.size)
    }

    @Test
    fun getPopularTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback)
                .onAllTvShowReceived(tvShowResponses)
            null
        }.`when`(remote).getPopularTvShow(any())
        val tvShowEntities = LiveDataTestUtil.getValue(filmRepository.getPopularTvShows())
        verify(remote).getPopularTvShow(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size, tvShowEntities.size)
    }

    @Test
    fun getTopRatedTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback)
                .onAllTvShowReceived(tvShowResponses)
            null
        }.`when`(remote).getTopRatedTvShow(any())
        val tvShowEntities = LiveDataTestUtil.getValue(filmRepository.getTopRatedTvShows())
        verify(remote).getTopRatedTvShow(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size, tvShowEntities.size)
    }

    @Test
    fun getAiringTodayTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback)
                .onAllTvShowReceived(tvShowResponses)
            null
        }.`when`(remote).getAiringTodayTvShow(any())
        val tvShowEntities = LiveDataTestUtil.getValue(filmRepository.getAiringTodayTvShows())
        verify(remote).getAiringTodayTvShow(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size, tvShowEntities.size)
    }

}