package com.jokot.app.moviecatalogue.ui.detail.movie

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jokot.app.moviecatalogue.R
import com.jokot.app.moviecatalogue.data.source.local.entity.ImagesEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieDetailEntity
import com.jokot.app.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.jokot.app.moviecatalogue.databinding.ContentDetailMovieBinding
import com.jokot.app.moviecatalogue.viewmodel.ViewModelFactory
import com.jokot.app.moviecatalogue.vo.Status

class DetailMovieActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE_ID = "extra movie id"
    }

    private lateinit var contentDetailMovieBinding: ContentDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        contentDetailMovieBinding = activityDetailMovieBinding.detailMovie

        setContentView(activityDetailMovieBinding.root)

        setSupportActionBar(activityDetailMovieBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_MOVIE_ID, 0)
            if (movieId != 0) {
                viewModel.setSelectedMovie(movieId)

                activityDetailMovieBinding.progressBar.visibility = View.VISIBLE
                contentDetailMovieBinding.root.visibility = View.GONE
                viewModel.getConfiguration().observe(this, { images ->
                    viewModel.movieDetail.observe(this, { movieDetailResource ->
                        if (movieDetailResource != null) {
                            when (movieDetailResource.status) {
                                Status.LOADING -> {
                                    activityDetailMovieBinding.progressBar.visibility = View.VISIBLE
                                    contentDetailMovieBinding.root.visibility = View.GONE
                                }
                                Status.SUCCESS -> {
                                    activityDetailMovieBinding.progressBar.visibility = View.GONE
                                    contentDetailMovieBinding.root.visibility = View.VISIBLE
                                    movieDetailResource.data?.let { populateMovie(it, images) }
                                }
                                Status.ERROR -> {
                                    activityDetailMovieBinding.progressBar.visibility = View.GONE
                                    contentDetailMovieBinding.root.visibility = View.VISIBLE
                                    Toast.makeText(
                                        applicationContext,
                                        "Terjadi kesalahan",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                            }
                        }
                    })
                })
            }
        }
    }

    private fun populateMovie(movie: MovieDetailEntity, images: ImagesEntity) {
        with(contentDetailMovieBinding) {
            textTitle.text = movie.title
            textDate.text = movie.releaseDate
            textDuration.text = movie.duration
            textYear.text = movie.releaseDate.takeLast(4)
            textGenre.text = movie.genres
            textScore.text = StringBuilder("${movie.voteAverage}%")
            textOverview.text = movie.overview

            val posterPath = images.secureBaseUrl + images.posterSizes[1] + movie.posterPath
            Glide.with(this@DetailMovieActivity)
                .load(posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(imgPoster)

            val backdropPath = images.secureBaseUrl + images.backdropSizes[1] + movie.backdropPath
            Glide.with(this@DetailMovieActivity)
                .load(backdropPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(imgBanner)
        }
    }
}