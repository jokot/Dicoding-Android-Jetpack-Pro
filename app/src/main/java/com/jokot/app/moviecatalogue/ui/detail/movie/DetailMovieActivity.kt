package com.jokot.app.moviecatalogue.ui.detail.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jokot.app.moviecatalogue.R
import com.jokot.app.moviecatalogue.data.source.local.entity.ImagesEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
import com.jokot.app.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.jokot.app.moviecatalogue.databinding.ContentDetailMovieBinding
import com.jokot.app.moviecatalogue.viewmodel.ViewModelFactory
import com.jokot.app.moviecatalogue.vo.Status

class DetailMovieActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE_ID = "extra movie id"
    }

    private lateinit var activityDetailMovieBinding: ActivityDetailMovieBinding
    private lateinit var contentDetailMovieBinding: ContentDetailMovieBinding

    private lateinit var viewModel: DetailMovieViewModel
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        contentDetailMovieBinding = activityDetailMovieBinding.detailMovie

        setContentView(activityDetailMovieBinding.root)

        setSupportActionBar(activityDetailMovieBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

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

    private fun populateMovie(movie: MovieEntity, images: ImagesEntity) {
        with(contentDetailMovieBinding) {
            textTitle.text = movie.title
            textDate.text = movie.releaseDate
            textDuration.text = movie.movieDetailEntity?.duration
            textYear.text = movie.releaseDate.takeLast(4)
            textGenre.text = movie.movieDetailEntity?.genres
            textScore.text = StringBuilder("${movie.score}%")
            textOverview.text = movie.overview

            val posterPath = images.secureBaseUrl + images.posterSizes[1] + movie.posterPath
            Glide.with(this@DetailMovieActivity)
                .load(posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(imgPoster)

            val backdropPath = images.secureBaseUrl + images.backdropSizes[1] + movie.bannerPath
            Glide.with(this@DetailMovieActivity)
                .load(backdropPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(imgBanner)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        viewModel.movieDetail.observe(this, { movieDetailResource ->
            if (movieDetailResource != null) {
                when (movieDetailResource.status) {
                    Status.LOADING -> {
                        activityDetailMovieBinding.progressBar.visibility = View.VISIBLE
                        contentDetailMovieBinding.root.visibility = View.GONE
                    }
                    Status.SUCCESS -> if (movieDetailResource.data != null) {
                        activityDetailMovieBinding.progressBar.visibility = View.GONE
                        contentDetailMovieBinding.root.visibility = View.VISIBLE
                        val state = movieDetailResource.data.favorite
                        setBookmarkState(state)
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
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite){
            viewModel.setFavorite()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBookmarkState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border)
        }
    }
}