package com.jokot.app.moviecatalogue.ui.detail

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jokot.app.moviecatalogue.R
import com.jokot.app.moviecatalogue.api.ApiConfig
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
import com.jokot.app.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.jokot.app.moviecatalogue.databinding.ContentDetailMovieBinding
import com.jokot.app.moviecatalogue.viewmodel.ViewModelFactory

class DetailMovieActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_FILM = "extra film"
        const val EXTRA_FILM_ID = "extra film id"

        @StringRes
        val FILM_TYPE = intArrayOf(R.string.movies, R.string.tv_shows)
    }

    private lateinit var contentDetailMovieBinding: ContentDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailFilmBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        contentDetailMovieBinding = activityDetailFilmBinding.detailFilm

        setContentView(activityDetailFilmBinding.root)

        setSupportActionBar(activityDetailFilmBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(ApiConfig.getApiService())
        val viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val filmId = extras.getString(EXTRA_FILM_ID)
            if (filmId != null) {
                viewModel.setSelectedMovie(filmId)
                
                val movie: MovieEntity = if (filmType == FILM_TYPE[0]) {
                    viewModel.getMovieDetail()
                } else {
                    viewModel.getTvShow()
                }
                populateFilm(movie)
            }
        }
    }

    private fun populateFilm(movie: MovieEntity) {
        with(contentDetailMovieBinding) {
            textTitle.text = movie.title
            textDate.text = movie.releaseDate
            textDuration.text = movie.duration
            textYear.text = movie.releaseDate.takeLast(4)
            textGenre.text = movie.genre
            textScore.text = StringBuilder("${movie.score}% ${getString(R.string.user_score)}")
            textOverview.text = movie.overview

            Glide.with(this@DetailMovieActivity)
                .load(movie.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(imgPoster)

            Glide.with(this@DetailMovieActivity)
                .load(movie.bannerPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(imgBanner)
        }
    }
}