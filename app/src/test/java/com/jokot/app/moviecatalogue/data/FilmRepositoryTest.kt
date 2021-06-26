package com.jokot.app.moviecatalogue.data


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.jokot.app.moviecatalogue.data.source.local.LocalDataSource
import com.jokot.app.moviecatalogue.data.source.local.entity.ImageEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity
import com.jokot.app.moviecatalogue.data.source.remote.RemoteDataSource
import com.jokot.app.moviecatalogue.utils.AppExecutors
import com.jokot.app.moviecatalogue.utils.DataDummy
import com.jokot.app.moviecatalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class FilmRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val filmRepository = FakeFilmRepository(remote, local, appExecutors)

    private val movieResponses = DataDummy.generateRemoteDummyMovie()
    private val movieId = movieResponses[0].id
    private val tvShowResponses = DataDummy.generateRemoteDummyTvShow()
    private val tvShowId = tvShowResponses[0].id
    private val imagesResponse = DataDummy.generateRemoteDummyConfig()

    private val movieDetailResponse = DataDummy.generateRemoteDummyMovieDetail()
    private val tvShowDetailResponse = DataDummy.generateRemoteDummyTvShowDetail()

    @Test
    fun getConfiguration() {
        val dummyConfig = MutableLiveData<ImageEntity>()
        dummyConfig.value = DataDummy.generateDummyConfig()
        `when`(local.getImage()).thenReturn(dummyConfig)

        val imagesEntity = LiveDataTestUtil.getValue(filmRepository.getConfiguration())

        verify(local).getImage()
        assertNotNull(imagesEntity)
        assertEquals(imagesResponse.posterSizes[0], imagesEntity.data?.posterSize)
    }

    @Test
    fun getMovieDetail() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = DataDummy.generateDummyMovieWithDetail(movieId)
        `when`(local.getMovieWithDetail(movieId)).thenReturn(dummyMovie)
        val movieEntity = LiveDataTestUtil.getValue(filmRepository.getMovieDetail(movieId))

        verify(local).getMovieWithDetail(movieId)

        assertNotNull(movieEntity)
        assertNotNull(movieEntity.data?.title)
        assertEquals(movieDetailResponse.title, movieEntity.data?.title)
    }

    @Test
    fun getTvShowDetail() {
        val dummyTvShow = MutableLiveData<TvShowEntity>()
        dummyTvShow.value = DataDummy.generateDummyTvShowWithDetail(tvShowId)
        `when`(local.getTvShowWithDetail(tvShowId)).thenReturn(dummyTvShow)
        val tvShowEntity = LiveDataTestUtil.getValue(filmRepository.getTvShowDetail(tvShowId))

        verify(local).getTvShowWithDetail(tvShowId)

        assertNotNull(tvShowEntity)
        assertNotNull(tvShowEntity.data?.title)
        assertEquals(tvShowDetailResponse.name, tvShowEntity.data?.title)
    }

    @Test
    fun getNowPlayingMovies() {
        val dummyMovies = MutableLiveData<List<MovieEntity>>()
        dummyMovies.value = DataDummy.generateDummyMovies()
        `when`(local.getNowPlayingMovies()).thenReturn(dummyMovies)
        val movieEntities = LiveDataTestUtil.getValue(filmRepository.getNowPlayingMovies())
        verify(local).getNowPlayingMovies()

        assertNotNull(movieEntities)
        assertEquals(movieResponses.size, movieEntities.data?.size)
    }

    @Test
    fun getPopularMovies() {
        val dummyMovies = MutableLiveData<List<MovieEntity>>()
        dummyMovies.value = DataDummy.generateDummyMovies()
        `when`(local.getPopularMovies()).thenReturn(dummyMovies)
        val movieEntities = LiveDataTestUtil.getValue(filmRepository.getPopularMovies())
        verify(local).getPopularMovies()

        assertNotNull(movieEntities)
        assertEquals(movieResponses.size, movieEntities.data?.size)
    }

    @Test
    fun getTopRatedMovie() {
        val dummyMovies = MutableLiveData<List<MovieEntity>>()
        dummyMovies.value = DataDummy.generateDummyMovies()
        `when`(local.getTopRatedMovies()).thenReturn(dummyMovies)
        val movieEntities = LiveDataTestUtil.getValue(filmRepository.getTopRatedMovies())
        verify(local).getTopRatedMovies()

        assertNotNull(movieEntities)
        assertEquals(movieResponses.size, movieEntities.data?.size)
    }

    @Test
    fun getUpcomingMovies() {
        val dummyMovies = MutableLiveData<List<MovieEntity>>()
        dummyMovies.value = DataDummy.generateDummyMovies()
        `when`(local.getUpcomingMovies()).thenReturn(dummyMovies)
        val movieEntities = LiveDataTestUtil.getValue(filmRepository.getUpcomingMovies())
        verify(local).getUpcomingMovies()

        assertNotNull(movieEntities)
        assertEquals(movieResponses.size, movieEntities.data?.size)
    }

    @Test
    fun getOnTheAirTvShows() {
        val dummyTvShows = MutableLiveData<List<TvShowEntity>>()
        dummyTvShows.value = DataDummy.generateDummyTvShows()
        `when`(local.getOnTheAirTvShows()).thenReturn(dummyTvShows)
        val tvShowEntities = LiveDataTestUtil.getValue(filmRepository.getOnTheAirTvShows())
        verify(local).getOnTheAirTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size, tvShowEntities.data?.size)
    }

    @Test
    fun getPopularTvShows() {
        val dummyTvShows = MutableLiveData<List<TvShowEntity>>()
        dummyTvShows.value = DataDummy.generateDummyTvShows()
        `when`(local.getPopularTvShows()).thenReturn(dummyTvShows)
        val tvShowEntities = LiveDataTestUtil.getValue(filmRepository.getPopularTvShows())
        verify(local).getPopularTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size, tvShowEntities.data?.size)
    }

    @Test
    fun getTopRatedTvShows() {
        val dummyTvShows = MutableLiveData<List<TvShowEntity>>()
        dummyTvShows.value = DataDummy.generateDummyTvShows()
        `when`(local.getTopRatedTvShows()).thenReturn(dummyTvShows)
        val tvShowEntities = LiveDataTestUtil.getValue(filmRepository.getTopRatedTvShows())
        verify(local).getTopRatedTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size, tvShowEntities.data?.size)
    }

    @Test
    fun getAiringTodayTvShows() {
        val dummyTvShows = MutableLiveData<List<TvShowEntity>>()
        dummyTvShows.value = DataDummy.generateDummyTvShows()
        `when`(local.getAiringTodayTvShows()).thenReturn(dummyTvShows)
        val tvShowEntities = LiveDataTestUtil.getValue(filmRepository.getAiringTodayTvShows())
        verify(local).getAiringTodayTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size, tvShowEntities.data?.size)
    }

    @Test
    fun getFavoriteMovies() {
        val dummyMovies = MutableLiveData<List<MovieEntity>>()
        dummyMovies.value = DataDummy.generateDummyMovies()
        `when`(local.getFavoriteMovie()).thenReturn(dummyMovies)
        val movieEntities = LiveDataTestUtil.getValue(filmRepository.getFavoriteMovie())
        verify(local).getFavoriteMovie()

        assertNotNull(movieEntities)
        assertEquals(movieResponses.size, movieEntities.size)
    }

    @Test
    fun getFavoriteTvShows() {
        val dummyTvShows = MutableLiveData<List<TvShowEntity>>()
        dummyTvShows.value = DataDummy.generateDummyTvShows()
        `when`(local.getFavoriteTvShow()).thenReturn(dummyTvShows)
        val tvShowEntities = LiveDataTestUtil.getValue(filmRepository.getFavoriteTvShow())
        verify(local).getFavoriteTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size, tvShowEntities.size)
    }
}