package com.jokot.app.moviecatalogue.ui.tv.airingtoday

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jokot.app.moviecatalogue.databinding.FragmentAiringTodayTvShowBinding
import com.jokot.app.moviecatalogue.ui.tv.TvShowAdapter
import com.jokot.app.moviecatalogue.ui.tv.TvShowViewModel
import com.jokot.app.moviecatalogue.viewmodel.ViewModelFactory
import com.jokot.app.moviecatalogue.vo.Status

class AiringTodayTvShowFragment : Fragment() {

    private var _fragmentAiringTodayTvShowBinding: FragmentAiringTodayTvShowBinding? = null
    private val binding get() = _fragmentAiringTodayTvShowBinding

    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentAiringTodayTvShowBinding =
            FragmentAiringTodayTvShowBinding.inflate(layoutInflater, container, false)
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
                viewModel.getAiringTodayTvShow().observe(viewLifecycleOwner, { tvShows ->
                    when (tvShows.status) {
                        Status.LOADING -> {
                            binding?.progressBar?.visibility = View.VISIBLE
                            binding?.rvTvShow?.visibility = View.GONE
                        }
                        Status.SUCCESS -> {
                            binding?.progressBar?.visibility = View.GONE
                            binding?.rvTvShow?.visibility = View.VISIBLE
                            tvShowAdapter.setTvShows(tvShows.data, images)
                            tvShowAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            })

            with(binding?.rvTvShow) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = tvShowAdapter
            }
        }
    }
}