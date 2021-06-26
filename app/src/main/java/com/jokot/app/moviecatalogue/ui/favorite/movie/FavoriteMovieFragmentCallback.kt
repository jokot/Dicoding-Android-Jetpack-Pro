package com.jokot.app.moviecatalogue.ui.favorite.movie

import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity

interface FavoriteMovieFragmentCallback {
    fun onShareClick(movie: MovieEntity)
}