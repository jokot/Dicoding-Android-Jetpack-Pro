package com.jokot.app.moviecatalogue.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jokot.app.moviecatalogue.databinding.FragmentTvShowBinding
import com.jokot.app.moviecatalogue.viewmodel.ViewModelFactory

class TvShowFragment : Fragment() {

    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            val tvShowAdapter = TvShowAdapter()

            fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
            viewModel.getConfiguration().observe(viewLifecycleOwner, { images ->
                viewModel.getInitData().getContentIfNotHandled()?.let {
                    it.observe(viewLifecycleOwner, { tvShows ->
                        fragmentTvShowBinding.progressBar.visibility = View.GONE
                        tvShowAdapter.setTvShows(tvShows, images)
                        tvShowAdapter.notifyDataSetChanged()
                    })
                }
            })

            fragmentTvShowBinding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
                fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
                viewModel.getConfiguration().observe(viewLifecycleOwner, { images ->
                    when (checkedId) {
                        fragmentTvShowBinding.chip1.id -> {
                            viewModel.getOnTheAirTvShow()
                                .observe(viewLifecycleOwner, { tvShows ->
                                    fragmentTvShowBinding.progressBar.visibility = View.GONE
                                    tvShowAdapter.setTvShows(tvShows, images)
                                    tvShowAdapter.notifyDataSetChanged()
                                })
                        }
                        fragmentTvShowBinding.chip2.id -> {
                            viewModel.getPopularTvShow()
                                .observe(viewLifecycleOwner, { tvShows ->
                                    fragmentTvShowBinding.progressBar.visibility = View.GONE
                                    tvShowAdapter.setTvShows(tvShows, images)
                                    tvShowAdapter.notifyDataSetChanged()
                                })
                        }
                        fragmentTvShowBinding.chip3.id -> {
                            viewModel.getTopRatedTvShow()
                                .observe(viewLifecycleOwner, { tvShows ->
                                    fragmentTvShowBinding.progressBar.visibility = View.GONE
                                    tvShowAdapter.setTvShows(tvShows, images)
                                    tvShowAdapter.notifyDataSetChanged()
                                })
                        }
                        fragmentTvShowBinding.chip4.id -> {
                            viewModel.getAiringTodayTvShow()
                                .observe(viewLifecycleOwner, { tvShows ->
                                    fragmentTvShowBinding.progressBar.visibility = View.GONE
                                    tvShowAdapter.setTvShows(tvShows, images)
                                    tvShowAdapter.notifyDataSetChanged()
                                })
                        }
                    }
                })
            }

            with(fragmentTvShowBinding.rvTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }
}