package com.jokot.app.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.jokot.app.moviecatalogue.R
import com.jokot.app.moviecatalogue.utils.DataDummy
import com.jokot.app.moviecatalogue.utils.EspressoIdlingResources
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {
    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyTvShows = DataDummy.generateDummyTvShows()
    // memiliki value Detail Movie dengan title "Mortal Kombat"
    private val dummyMovieDetail = DataDummy.generateDummyMovieDetail()
    // memiliki value Detail Tv Show dengan name "The Flash"
    private val dummyTvShowDetail = DataDummy.generateDummyTvShowDetail()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource)
    }

    @Test
    fun loadNowPlayingMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadUpcomingMovie() {
        onView(withText(R.string.upcoming)).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withText(dummyMovieDetail.title)).perform(click())

        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(dummyMovieDetail.title)))
        onView(withId(R.id.text_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.text_overview)).check(matches(withText(dummyMovieDetail.overview)))
        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(withText(dummyMovieDetail.releaseDate)))
        onView(withId(R.id.text_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genre)).check(matches(withText(dummyMovieDetail.genres.joinToString())))
        onView(withId(R.id.text_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.text_duration)).check(matches(withText(dummyMovieDetail.duration)))
        onView(withId(R.id.text_score)).check(matches(isDisplayed()))
        onView(withId(R.id.text_score)).check(matches(withText("${dummyMovieDetail.voteAverage}%")))
        onView(withId(R.id.text_year)).check(matches(isDisplayed()))
        onView(withId(R.id.text_year)).check(
            matches(
                withText(
                    dummyMovieDetail.releaseDate.takeLast(
                        4
                    )
                )
            )
        )
    }

    @Test
    fun loadOnAirTvShow() {
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShows.size
            )
        )
    }

    @Test
    fun loadAiringTodayTvShow() {
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withText(R.string.airing_today)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShows.size
            )
        )
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withText(dummyTvShowDetail.name)).perform(click())

        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(dummyTvShowDetail.name)))
        onView(withId(R.id.text_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.text_overview)).check(matches(withText(dummyTvShowDetail.overview)))
        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(withText(dummyTvShowDetail.firstAirDate)))
        onView(withId(R.id.text_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genre)).check(matches(withText(dummyTvShowDetail.genres.joinToString())))
        onView(withId(R.id.text_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.text_duration)).check(matches(withText(dummyTvShowDetail.episodeRunTime)))
        onView(withId(R.id.text_score)).check(matches(isDisplayed()))
        onView(withId(R.id.text_score)).check(matches(withText("${dummyTvShowDetail.voteAverage}%")))
        onView(withId(R.id.text_year)).check(matches(isDisplayed()))
        onView(withId(R.id.text_year)).check(
            matches(
                withText(
                    dummyTvShowDetail.firstAirDate.takeLast(
                        4
                    )
                )
            )
        )

    }
}