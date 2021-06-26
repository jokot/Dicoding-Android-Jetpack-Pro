package com.jokot.app.moviecatalogue.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jokot.app.moviecatalogue.databinding.FragmentMovieBinding
import com.jokot.app.moviecatalogue.ui.movie.nowplaying.NowPlayingMovieFragment
import com.jokot.app.moviecatalogue.ui.movie.popular.PopularMovieFragment
import com.jokot.app.moviecatalogue.ui.movie.toprated.TopRatedMovieFragment
import com.jokot.app.moviecatalogue.ui.movie.upcoming.UpcomingMovieFragment

class MovieFragment : Fragment() {

    private var _fragmentMovieBinding: FragmentMovieBinding? = null
    private val binding get() = _fragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            addFragment(NowPlayingMovieFragment())

            binding?.chipGroup?.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    binding?.chip1?.id -> {
                        changeFragment(NowPlayingMovieFragment())
                    }
                    binding?.chip2?.id -> {
                        changeFragment(PopularMovieFragment())
                    }
                    binding?.chip3?.id -> {
                        changeFragment(TopRatedMovieFragment())
                    }
                    binding?.chip4?.id -> {
                        changeFragment(UpcomingMovieFragment())
                    }
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