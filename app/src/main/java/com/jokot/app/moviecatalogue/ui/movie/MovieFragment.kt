package com.jokot.app.moviecatalogue.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jokot.app.moviecatalogue.databinding.FragmentMovieBinding
import com.jokot.app.moviecatalogue.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            val movieAdapter = MovieAdapter()

            fragmentMovieBinding.progressBar.visibility = View.VISIBLE
            viewModel.getConfiguration().observe(viewLifecycleOwner, { images ->
                viewModel.getInitData().getContentIfNotHandled()?.let {
                    it.observe(viewLifecycleOwner, { movies ->
                        fragmentMovieBinding.progressBar.visibility = View.GONE
                        movieAdapter.setMovies(movies, images)
                        movieAdapter.notifyDataSetChanged()
                    })
                }
            })

            fragmentMovieBinding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
                fragmentMovieBinding.progressBar.visibility = View.VISIBLE
                viewModel.getConfiguration().observe(viewLifecycleOwner, { images ->
                    when (checkedId) {
                        fragmentMovieBinding.chip1.id -> {
                            viewModel.getNowPlayingMovies().observe(viewLifecycleOwner, { movies ->
                                fragmentMovieBinding.progressBar.visibility = View.GONE
                                movieAdapter.setMovies(movies, images)
                                movieAdapter.notifyDataSetChanged()
                            })
                        }
                        fragmentMovieBinding.chip2.id -> {
                            viewModel.getPopularMovies().observe(viewLifecycleOwner, { movies ->
                                fragmentMovieBinding.progressBar.visibility = View.GONE
                                movieAdapter.setMovies(movies, images)
                                movieAdapter.notifyDataSetChanged()
                            })
                        }
                        fragmentMovieBinding.chip3.id -> {
                            viewModel.getTopRatedMovies().observe(viewLifecycleOwner, { movies ->
                                fragmentMovieBinding.progressBar.visibility = View.GONE
                                movieAdapter.setMovies(movies, images)
                                movieAdapter.notifyDataSetChanged()
                            })
                        }
                        fragmentMovieBinding.chip4.id -> {
                            viewModel.getUpcomingMovies().observe(viewLifecycleOwner, { movies ->
                                fragmentMovieBinding.progressBar.visibility = View.GONE
                                movieAdapter.setMovies(movies, images)
                                movieAdapter.notifyDataSetChanged()
                            })
                        }

                    }
                })
            }

            with(fragmentMovieBinding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}