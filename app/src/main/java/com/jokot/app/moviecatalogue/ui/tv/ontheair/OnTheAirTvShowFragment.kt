package com.jokot.app.moviecatalogue.ui.tv.ontheair

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jokot.app.moviecatalogue.data.source.local.entity.ImageEntity
import com.jokot.app.moviecatalogue.databinding.FragmentOnTheAirTvShowBinding
import com.jokot.app.moviecatalogue.ui.tv.TvShowAdapter
import com.jokot.app.moviecatalogue.ui.tv.TvShowViewModel
import com.jokot.app.moviecatalogue.viewmodel.ViewModelFactory
import com.jokot.app.moviecatalogue.vo.Status

class OnTheAirTvShowFragment : Fragment() {

    private var _fragmentOnTheAirTvShowBinding: FragmentOnTheAirTvShowBinding? = null
    private val binding get() = _fragmentOnTheAirTvShowBinding

    private lateinit var tvShowAdapter: TvShowAdapter
    private lateinit var viewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentOnTheAirTvShowBinding =
            FragmentOnTheAirTvShowBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            tvShowAdapter = TvShowAdapter()

            observeGetConfig()

            with(binding?.rvTvShow) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = tvShowAdapter
            }
        }
    }

    private fun observeGetConfig() {
        viewModel.getConfiguration().observe(viewLifecycleOwner, { imageResource ->
            if (imageResource != null){
                when(imageResource.status){
                    Status.LOADING ->{
                        binding?.progressBar?.visibility = View.VISIBLE
                    }
                    Status.SUCCESS ->{
                        imageResource.data?.let { observeGetTvShow(it) }
                    }
                    Status.ERROR ->{
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun observeGetTvShow(image: ImageEntity) {
        viewModel.getOnTheAirTvShow().observe(viewLifecycleOwner, { tvShows ->
            when (tvShows.status) {
                Status.LOADING -> {
                    binding?.progressBar?.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding?.progressBar?.visibility = View.GONE
                    tvShowAdapter.setImage(image)
                    tvShowAdapter.submitList(tvShows.data)
                    tvShowAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    binding?.progressBar?.visibility = View.GONE
                    Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}