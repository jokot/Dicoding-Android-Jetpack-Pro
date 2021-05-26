package com.jokot.app.moviecatalogue.ui.detail.tv

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.jokot.app.moviecatalogue.R
import com.jokot.app.moviecatalogue.data.source.local.entity.ImagesEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowDetailEntity
import com.jokot.app.moviecatalogue.databinding.ActivityDetailTvShowBinding
import com.jokot.app.moviecatalogue.databinding.ContentDetailTvShowBinding
import com.jokot.app.moviecatalogue.viewmodel.ViewModelFactory

class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV_SHOW_ID = "extra tv show id"
    }

    private lateinit var contentDetailTvShowBinding: ContentDetailTvShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        contentDetailTvShowBinding = activityDetailTvShowBinding.detailTvShow

        setContentView(activityDetailTvShowBinding.root)

        setSupportActionBar(activityDetailTvShowBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailTvShowViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getInt(EXTRA_TV_SHOW_ID, 0)
            if (tvShowId != 0) {
                viewModel.setSelectedTvShow(tvShowId)

                activityDetailTvShowBinding.progressBar.visibility = View.VISIBLE
                contentDetailTvShowBinding.root.visibility = View.GONE
                viewModel.getConfiguration().observe(this, { images ->
                    viewModel.getTvShowDetail().observe(this, { tvShowDetail ->
                        activityDetailTvShowBinding.progressBar.visibility = View.GONE
                        contentDetailTvShowBinding.root.visibility = View.VISIBLE
                        populateTvShow(tvShowDetail, images)
                    })
                })
            }
        }
    }

    private fun populateTvShow(tvShow: TvShowDetailEntity, images: ImagesEntity) {
        with(contentDetailTvShowBinding) {
            textTitle.text = tvShow.name
            textDate.text = tvShow.firstAirDate
            textDuration.text = tvShow.episodeRunTime
            textGenre.text = tvShow.genres.joinToString()
            textOverview.text = tvShow.overview
            textScore.text = StringBuilder("${tvShow.voteAverage}%")
            textYear.text = tvShow.firstAirDate.takeLast(4)

            val posterPath = images.secureBaseUrl + images.posterSizes[1] + tvShow.posterPath
            Glide.with(this@DetailTvShowActivity)
                .load(posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                )
                .into(imgPoster)

            val backdropPath = images.secureBaseUrl + images.backdropSizes[1] + tvShow.backdropPath
            Glide.with(this@DetailTvShowActivity)
                .load(backdropPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                )
                .into(imgBanner)
        }
    }
}