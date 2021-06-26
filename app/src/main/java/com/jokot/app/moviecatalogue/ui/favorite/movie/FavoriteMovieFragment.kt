package com.jokot.app.moviecatalogue.ui.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jokot.app.moviecatalogue.data.source.local.entity.ImageEntity
import com.jokot.app.moviecatalogue.databinding.FragmentFavoriteMovieBinding
import com.jokot.app.moviecatalogue.ui.favorite.FavoriteViewModel
import com.jokot.app.moviecatalogue.ui.movie.MovieAdapter
import com.jokot.app.moviecatalogue.viewmodel.ViewModelFactory
import com.jokot.app.moviecatalogue.vo.Status


class FavoriteMovieFragment : Fragment() {

    private var _fragmentFavoriteMovieBinding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _fragmentFavoriteMovieBinding

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentFavoriteMovieBinding =
            FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            movieAdapter = MovieAdapter()

            observeGetConfig()

            with(binding?.rvFavorite) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = movieAdapter
            }
        }
    }

    private fun observeGetConfig(){
        viewModel.getConfiguration().observe(viewLifecycleOwner, { imageResource ->
            if (imageResource != null){
                when(imageResource.status){
                    Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        imageResource.data?.let { observeGetFavorite(it) }
                    }
                    Status.ERROR -> {
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(requireActivity(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun observeGetFavorite(image: ImageEntity){
        viewModel.getFavoriteMovies().observe(viewLifecycleOwner, { movies ->
            binding?.progressBar?.visibility = View.GONE
            movieAdapter.setMovies(movies, image)
            movieAdapter.notifyDataSetChanged()
        })
    }
}
