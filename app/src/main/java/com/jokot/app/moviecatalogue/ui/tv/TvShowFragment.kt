package com.jokot.app.moviecatalogue.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jokot.app.moviecatalogue.databinding.FragmentTvShowBinding
import com.jokot.app.moviecatalogue.ui.tv.airingtoday.AiringTodayTvShowFragment
import com.jokot.app.moviecatalogue.ui.tv.ontheair.OnTheAirTvShowFragment
import com.jokot.app.moviecatalogue.ui.tv.popular.PopularTvShowFragment
import com.jokot.app.moviecatalogue.ui.tv.toprated.TopRatedTvShowFragment

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
            addFragment(OnTheAirTvShowFragment())
            binding?.chipGroup?.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    binding?.chip1?.id -> {
                        changeFragment(OnTheAirTvShowFragment())
                    }
                    binding?.chip2?.id -> {
                        changeFragment(PopularTvShowFragment())
                    }
                    binding?.chip3?.id -> {
                        changeFragment(TopRatedTvShowFragment())
                    }
                    binding?.chip4?.id -> {
                        changeFragment(AiringTodayTvShowFragment())
                    }
                }

            }
        }
    }

    private fun addFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        binding?.fragmentContainer?.id?.let {
            transaction.add(it, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun changeFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        binding?.fragmentContainer?.id?.let {
            transaction.replace(it, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}