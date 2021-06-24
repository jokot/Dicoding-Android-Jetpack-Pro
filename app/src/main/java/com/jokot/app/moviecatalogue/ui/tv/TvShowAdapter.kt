package com.jokot.app.moviecatalogue.ui.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jokot.app.moviecatalogue.R
import com.jokot.app.moviecatalogue.data.source.local.entity.ImagesEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity
import com.jokot.app.moviecatalogue.databinding.ItemsTvShowBinding
import com.jokot.app.moviecatalogue.ui.detail.tv.DetailTvShowActivity

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private val listTvShows = ArrayList<TvShowEntity>()
    private lateinit var images: ImagesEntity

    fun setTvShows(tvShows: List<TvShowEntity>?, images: ImagesEntity) {
        if (tvShows.isNullOrEmpty()) return
        this.listTvShows.clear()
        this.listTvShows.addAll(tvShows)
        this.images = images
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemsTvShowBinding =
            ItemsTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemsTvShowBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = listTvShows[position]
        holder.bind(tvShow, images)
    }

    override fun getItemCount(): Int = listTvShows.size

    class TvShowViewHolder(private val binding: ItemsTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity, images: ImagesEntity) {
            with(binding) {
                tvItemTitle.text = tvShow.title
                tvItemDate.text = tvShow.releaseDate
                tvItemOverview.text = tvShow.overview

                itemView.setOnClickListener {
                    val intent = Intent(it.context, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW_ID, tvShow.id)
                    itemView.context.startActivity(intent)
                }

                val posterPath = images.secureBaseUrl + images.posterSizes[0] + tvShow.posterPath
                Glide.with(itemView.context)
                    .load(posterPath)
                    .apply(
                        RequestOptions().placeholder(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
            }
        }
    }
}