package com.jokot.app.moviecatalogue.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jokot.app.moviecatalogue.databinding.FragmentFavoriteBinding
import com.jokot.app.moviecatalogue.ui.movie.MovieAdapter
import com.jokot.app.moviecatalogue.ui.tv.TvShowAdapter
import com.jokot.app.moviecatalogue.viewmodel.ViewModelFactory


class FavoriteFragment : Fragment() {
    private var _fragmentFavoriteBinding: FragmentFavoriteBinding? = null
    private val binding get() = _fragmentFavoriteBinding

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var tvShowAdapter: TvShowAdapter

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
        if (activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[BookmarkViewModel::class.java]

            movieAdapter = MovieAdapter()
            tvShowAdapter = TvShowAdapter()

            binding?.progressBar?.visibility = View.VISIBLE
            viewModel.getConfiguration().observe(viewLifecycleOwner, { images ->
                viewModel.getInitData().getContentIfNotHandled()?.let {
                    it.observe(viewLifecycleOwner, { movies ->
                        binding?.progressBar?.visibility = View.GONE
                        movieAdapter.setMovies(movies, images)
                        movieAdapter.notifyDataSetChanged()
                    })
                }
            })

            binding?.chipGroup?.setOnCheckedChangeListener { _, checkedId ->
                binding?.progressBar?.visibility = View.VISIBLE
                viewModel.getConfiguration().observe(viewLifecycleOwner, { images ->
                    when (checkedId) {
                        binding?.chip1?.id -> {
                            viewModel.getBookmarkMovies().observe(viewLifecycleOwner, { movies ->
                                binding?.progressBar?.visibility = View.GONE
                                movieAdapter.setMovies(movies, images)
                                movieAdapter.notifyDataSetChanged()
                            })
                            with(binding?.rvBookmark) {
                                this?.adapter = movieAdapter
                            }
                        }

                        binding?.chip2?.id -> {
                            viewModel.getBookmarkTvShows().observe(viewLifecycleOwner, { tvShows ->
                                binding?.progressBar?.visibility = View.GONE
                                tvShowAdapter.setTvShows(tvShows, images)
                                tvShowAdapter.notifyDataSetChanged()
                            })
                            with(binding?.rvBookmark) {
                                this?.adapter = tvShowAdapter
                            }
                        }
                    }
                })
            }


            with(binding?.rvBookmark) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = movieAdapter
            }
        }
    }
}