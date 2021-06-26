package com.jokot.app.moviecatalogue.data


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.jokot.app.moviecatalogue.data.source.local.LocalDataSource
import com.jokot.app.moviecatalogue.data.source.local.entity.ImageEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity
import com.jokot.app.moviecatalogue.data.source.remote.RemoteDataSource
import com.jokot.app.moviecatalogue.utils.AppExecutors
import com.jokot.app.moviecatalogue.utils.DataDummy
import com.jokot.app.moviecatalogue.utils.LiveDataTestUtil
import com.jokot.app.moviecatalogue.utils.PagedListUtil
import com.jokot.app.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner


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
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getNowPlayingMovies()).thenReturn(dataSourceFactory)
        filmRepository.getNowPlayingMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getNowPlayingMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size, movieEntities.data?.size)
    }

    @Test
    fun getPopularMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getPopularMovies()).thenReturn(dataSourceFactory)
        filmRepository.getPopularMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getPopularMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size, movieEntities.data?.size)
    }

    @Test
    fun getTopRatedMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getTopRatedMovies()).thenReturn(dataSourceFactory)
        filmRepository.getTopRatedMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getTopRatedMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size, movieEntities.data?.size)
    }

    @Test
    fun getUpcomingMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getUpcomingMovies()).thenReturn(dataSourceFactory)
        filmRepository.getUpcomingMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getUpcomingMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size, movieEntities.data?.size)
    }

    @Test
    fun getOnTheAirTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getOnTheAirTvShows()).thenReturn(dataSourceFactory)
        filmRepository.getOnTheAirTvShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getOnTheAirTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size, tvShowEntities.data?.size)
    }

    @Test
    fun getPopularTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getPopularTvShows()).thenReturn(dataSourceFactory)
        filmRepository.getPopularTvShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getPopularTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size, tvShowEntities.data?.size)
    }

    @Test
    fun getTopRatedTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getTopRatedTvShows()).thenReturn(dataSourceFactory)
        filmRepository.getTopRatedTvShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getTopRatedTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size, tvShowEntities.data?.size)
    }

    @Test
    fun getAiringTodayTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAiringTodayTvShows()).thenReturn(dataSourceFactory)
        filmRepository.getAiringTodayTvShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getAiringTodayTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size, tvShowEntities.data?.size)
    }

    @Test
    fun getFavoriteMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovie()).thenReturn(dataSourceFactory)
        filmRepository.getFavoriteMovie()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getFavoriteMovie()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size, movieEntities.data?.size)
    }

    @Test
    fun getFavoriteTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoriteTvShow()).thenReturn(dataSourceFactory)
        filmRepository.getFavoriteTvShow()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getFavoriteTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size, tvShowEntities.data?.size)
    }
}