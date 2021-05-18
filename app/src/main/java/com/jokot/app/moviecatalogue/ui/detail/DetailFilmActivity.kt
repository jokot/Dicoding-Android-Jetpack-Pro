package com.jokot.app.moviecatalogue.ui.detail

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jokot.app.moviecatalogue.R
import com.jokot.app.moviecatalogue.data.FilmEntity
import com.jokot.app.moviecatalogue.databinding.ActivityDetailFilmBinding
import com.jokot.app.moviecatalogue.databinding.ContentDetailFilmBinding

class DetailFilmActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_FILM = "extra film"
        const val EXTRA_FILM_ID = "extra film id"

        @StringRes
        val FILM_TYPE = intArrayOf(R.string.movies, R.string.tv_shows)
    }

    private lateinit var contentDetailFilmBinding: ContentDetailFilmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailFilmBinding = ActivityDetailFilmBinding.inflate(layoutInflater)
        contentDetailFilmBinding = activityDetailFilmBinding.detailFilm

        setContentView(activityDetailFilmBinding.root)

        setSupportActionBar(activityDetailFilmBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailFilmViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val filmType = extras.getInt(EXTRA_FILM)
            val filmId = extras.getString(EXTRA_FILM_ID)
            if (filmId != null) {
                viewModel.setSelectedFilm(filmId)
                val film: FilmEntity = if (filmType == FILM_TYPE[0]) {
                    viewModel.getMovie()
                } else {
                    viewModel.getTvShow()
                }
                populateFilm(film)
            }
        }
    }

    private fun populateFilm(film: FilmEntity) {
        with(contentDetailFilmBinding) {
            textTitle.text = film.title
            textDate.text = film.releaseDate
            textDuration.text = film.duration
            textYear.text = film.releaseDate.takeLast(4)
            textGenre.text = film.genre
            textScore.text = StringBuilder("${film.score}% ${getString(R.string.user_score)}")
            textOverview.text = film.overview

            Glide.with(this@DetailFilmActivity)
                .load(film.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(imgPoster)

            Glide.with(this@DetailFilmActivity)
                .load(film.bannerPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(imgBanner)
        }
    }
}