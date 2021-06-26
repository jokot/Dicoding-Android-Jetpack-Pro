package com.jokot.app.moviecatalogue.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jokot.app.moviecatalogue.databinding.FragmentFavoriteBinding
import com.jokot.app.moviecatalogue.ui.favorite.movie.FavoriteMovieFragment
import com.jokot.app.moviecatalogue.ui.favorite.tvshow.FavoriteTvShowFragment
import com.jokot.app.moviecatalogue.ui.movie.MovieAdapter
import com.jokot.app.moviecatalogue.ui.tv.TvShowAdapter


class FavoriteFragment : Fragment() {
    private var _fragmentFavoriteBinding: FragmentFavoriteBinding? = null
    private val binding get() = _fragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentFavoriteBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            addFragment(FavoriteMovieFragment())
            binding?.chipGroup?.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    binding?.chip1?.id -> changeFragment(FavoriteMovieFragment())
                    binding?.chip2?.id -> changeFragment(FavoriteTvShowFragment())
                }
            }
        }
    }

    private fun addFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        binding?.fragmentContainer?.id?.let {
            transaction.add(it, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun changeFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        binding?.fragmentContainer?.id?.let {
            transaction.replace(it, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}