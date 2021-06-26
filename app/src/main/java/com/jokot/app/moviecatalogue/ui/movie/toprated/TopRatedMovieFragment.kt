package com.jokot.app.moviecatalogue.ui.movie.toprated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jokot.app.moviecatalogue.data.source.local.entity.ImageEntity
import com.jokot.app.moviecatalogue.databinding.FragmentTopRatedMovieBinding
import com.jokot.app.moviecatalogue.ui.movie.MovieAdapter
import com.jokot.app.moviecatalogue.ui.movie.MovieViewModel
import com.jokot.app.moviecatalogue.viewmodel.ViewModelFactory
import com.jokot.app.moviecatalogue.vo.Status


class TopRatedMovieFragment : Fragment() {

    private var _fragmentTopRatedMovieBinding: FragmentTopRatedMovieBinding? = null
    private val binding get() = _fragmentTopRatedMovieBinding

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentTopRatedMovieBinding =
            FragmentTopRatedMovieBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            movieAdapter = MovieAdapter()

            observeGetConfig()

            with(binding?.rvMovie) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = movieAdapter
            }
        }
    }

    private fun observeGetConfig() {
        viewModel.getConfiguration().observe(viewLifecycleOwner, { imageResource ->
            if (imageResource != null){
                when(imageResource.status){
                    Status.LOADING ->{
                        binding?.progressBar?.visibility = View.VISIBLE
                        binding?.rvMovie?.visibility = View.GONE
                    }
                    Status.SUCCESS ->{
                        imageResource.data?.let { observeGetMovie(it) }
                    }
                    Status.ERROR ->{
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun observeGetMovie(image: ImageEntity) {
        viewModel.getTopRatedMovies().observe(viewLifecycleOwner, { movies ->
            when (movies.status) {
                Status.LOADING -> {
                    binding?.progressBar?.visibility = View.VISIBLE
                    binding?.rvMovie?.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    binding?.progressBar?.visibility = View.GONE
                    binding?.rvMovie?.visibility = View.VISIBLE
                    movieAdapter.setMovies(movies.data, image)
                    movieAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    binding?.progressBar?.visibility = View.GONE
                    Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}