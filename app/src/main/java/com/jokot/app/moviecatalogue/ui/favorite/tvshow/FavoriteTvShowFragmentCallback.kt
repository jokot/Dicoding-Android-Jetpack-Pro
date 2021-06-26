package com.jokot.app.moviecatalogue.ui.favorite.tvshow

import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity

interface FavoriteTvShowFragmentCallback {
    fun onShareClick(tvShow: TvShowEntity)
}