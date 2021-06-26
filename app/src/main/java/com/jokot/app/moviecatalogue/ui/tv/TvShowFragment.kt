package com.jokot.app.moviecatalogue.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jokot.app.moviecatalogue.data.source.local.entity.ImagesEntity
import com.jokot.app.moviecatalogue.data.source.local.entity.TvShowEntity
import com.jokot.app.moviecatalogue.databinding.FragmentTvShowBinding
import com.jokot.app.moviecatalogue.viewmodel.ViewModelFactory
import com.jokot.app.moviecatalogue.vo.Resource
import com.jokot.app.moviecatalogue.vo.Status

class TvShowFragment : Fragment() {

    private var _fragmentTvShowBinding: FragmentTvShowBinding? = null
    private val binding get() = _fragmentTvShowBinding

    private var tvShowAdapter: TvShowAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            tvShowAdapter = TvShowAdapter()

            binding?.progressBar?.visibility = View.VISIBLE
            binding?.rvTvShow?.visibility = View.GONE
            viewModel.getConfiguration().observe(viewLifecycleOwner, { images ->
                viewModel.getInitData().getContentIfNotHandled()?.let {
                    it.observe(viewLifecycleOwner, { tvShows ->
                        tvShows?.let { setupUiOnStatus(tvShows, images) }
                    })
                }
            })

            binding?.chipGroup?.setOnCheckedChangeListener { _, checkedId ->
                binding?.progressBar?.visibility = View.VISIBLE
                binding?.rvTvShow?.visibility = View.GONE
                viewModel.getConfiguration().observe(viewLifecycleOwner, { images ->
                    when (checkedId) {
                        binding?.chip1?.id -> {
                            viewModel.getOnTheAirTvShow()
                                .observe(viewLifecycleOwner, { tvShows ->
                                    tvShows?.let { setupUiOnStatus(tvShows, images) }
                                })
                        }
                        binding?.chip2?.id -> {
                            viewModel.getPopularTvShow()
                                .observe(viewLifecycleOwner, { tvShows ->
                                    tvShows?.let { setupUiOnStatus(tvShows, images) }
                                })
                        }
                        binding?.chip3?.id -> {
                            viewModel.getTopRatedTvShow()
                                .observe(viewLifecycleOwner, { tvShows ->
                                    tvShows?.let { setupUiOnStatus(tvShows, images) }
                                })
                        }
                        binding?.chip4?.id -> {
                            viewModel.getAiringTodayTvShow()
                                .observe(viewLifecycleOwner, { tvShows ->
                                    tvShows?.let { setupUiOnStatus(tvShows, images) }
                                })
                        }
                    }
                })
            }
        }
    }

    private fun setupUiOnStatus(tvShows: Resource<List<TvShowEntity>>, images: ImagesEntity) {
        when (tvShows.status) {
            Status.LOADING -> {
                tvShowAdapter = null
                binding?.progressBar?.visibility = View.VISIBLE
                binding?.rvTvShow?.visibility = View.GONE
            }
            Status.SUCCESS -> {
                binding?.progressBar?.visibility = View.GONE
                binding?.rvTvShow?.visibility = View.VISIBLE

                tvShowAdapter = TvShowAdapter()

                with(binding?.rvTvShow) {
                    this?.layoutManager = LinearLayoutManager(context)
                    this?.setHasFixedSize(true)
                    this?.adapter = tvShowAdapter
                }

                tvShowAdapter?.setTvShows(tvShows.data, images)
                tvShowAdapter?.notifyDataSetChanged()
            }
            Status.ERROR -> {
                binding?.progressBar?.visibility = View.GONE
                Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}