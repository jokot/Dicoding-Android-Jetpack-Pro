package com.jokot.app.moviecatalogue.ui.movie.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jokot.app.moviecatalogue.databinding.FragmentUpcomingMovieBinding
import com.jokot.app.moviecatalogue.ui.movie.MovieAdapter
import com.jokot.app.moviecatalogue.ui.movie.MovieViewModel
import com.jokot.app.moviecatalogue.viewmodel.ViewModelFactory
import com.jokot.app.moviecatalogue.vo.Status

class UpcomingMovieFragment : Fragment() {

    private var _fragmentUpcomingMovieBinding: FragmentUpcomingMovieBinding? = null
    private val binding get() = _fragmentUpcomingMovieBinding

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentUpcomingMovieBinding =
            FragmentUpcomingMovieBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            movieAdapter = MovieAdapter()

            binding?.progressBar?.visibility = View.GONE
            binding?.rvMovie?.visibility = View.GONE
            viewModel.getConfiguration().observe(viewLifecycleOwner, { images ->
                viewModel.getUpcomingMovies().observe(viewLifecycleOwner, { movies ->
                    when (movies.status) {
                        Status.LOADING -> {
                            binding?.progressBar?.visibility = View.VISIBLE
                            binding?.rvMovie?.visibility = View.GONE
                        }
                        Status.SUCCESS -> {
                            binding?.progressBar?.visibility = View.GONE
                            binding?.rvMovie?.visibility = View.VISIBLE
                            movieAdapter.setMovies(movies.data, images)
                            movieAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            })

            with(binding?.rvMovie) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = movieAdapter
            }
        }
    }
}