package com.jokot.app.moviecatalogue.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jokot.app.moviecatalogue.data.source.local.entity.ImagesEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.MovieEntity
import com.jokot.app.moviecatalogue.databinding.FragmentMovieBinding
import com.jokot.app.moviecatalogue.viewmodel.ViewModelFactory
import com.jokot.app.moviecatalogue.vo.Resource
import com.jokot.app.moviecatalogue.vo.Status

class MovieFragment : Fragment() {

    private var _fragmentMovieBinding: FragmentMovieBinding? = null
    private val binding get() = _fragmentMovieBinding

    private lateinit var movieAdapter: MovieAdapter


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
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            movieAdapter = MovieAdapter()

            binding?.progressBar?.visibility = View.VISIBLE
            viewModel.getConfiguration().observe(viewLifecycleOwner, { images ->
                viewModel.getInitData().getContentIfNotHandled()?.let { it ->
                    it.observe(viewLifecycleOwner, { movies ->
                        movies?.let { setupOnStatus(movies, images) }
                    })
                }
            })

            binding?.chipGroup?.setOnCheckedChangeListener { _, checkedId ->
                binding?.progressBar?.visibility = View.VISIBLE
                viewModel.getConfiguration().observe(viewLifecycleOwner, { images ->
                    when (checkedId) {
                        binding?.chip1?.id -> {
                            viewModel.getNowPlayingMovies().observe(viewLifecycleOwner, { movies ->
                                movies?.let { setupOnStatus(movies, images) }
                            })
                        }
                        binding?.chip2?.id -> {
                            viewModel.getPopularMovies().observe(viewLifecycleOwner, { movies ->
                                movies?.let { setupOnStatus(movies, images) }
                            })
                        }
                        binding?.chip3?.id -> {
                            viewModel.getTopRatedMovies().observe(viewLifecycleOwner, { movies ->
                                movies?.let { setupOnStatus(movies, images) }
                            })
                        }
                        binding?.chip4?.id -> {
                            viewModel.getUpcomingMovies().observe(viewLifecycleOwner, { movies ->
                                movies?.let { setupOnStatus(movies, images) }
                            })
                        }

                    }
                })
            }

            with(binding?.rvMovie) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = movieAdapter
            }
        }
    }

    private fun setupOnStatus(movies: Resource<List<MovieEntity>>, images: ImagesEntity) {
        when (movies.status) {
            Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
            Status.SUCCESS -> {
                binding?.progressBar?.visibility = View.GONE
                movieAdapter.setMovies(movies.data, images)
                movieAdapter.notifyDataSetChanged()
            }
            Status.ERROR -> {
                binding?.progressBar?.visibility = View.GONE
                Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}