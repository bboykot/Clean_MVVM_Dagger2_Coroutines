package com.bboykot.weather.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bboykot.weather.R
import com.bboykot.weather.databinding.FragmentHomeBinding
import com.bboykot.weather.domain.models.CurrentForecast
import com.bboykot.weather.domain.models.Resource
import com.bboykot.weather.presentation.common.MainActivity
import com.bboykot.weather.presentation.common.ViewModelFactory
import com.bboykot.weather.presentation.day.DayForecastFragment
import com.bboykot.weather.presentation.week.WeekForecastFragment
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).homeComponent.injectHomeFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnDayForecast.setOnClickListener { showDayForecast() }
        binding.btnWeekForecast.setOnClickListener { showWeekForecast() }

        viewModel.defaultCity.observe(viewLifecycleOwner, Observer {
            viewModel.loadForecast()
        })

        viewModel.result.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Success<CurrentForecast> -> {
                    binding.groupData.isVisible = true
                    binding.tvError.isVisible = false
                    binding.progressBar.isVisible = false

                    binding.tvCity.text = result.data.city
                    binding.tvTemp.text = result.data.temperature.toString()
                    binding.tvWind.text = result.data.wind.toString()
                    binding.tvDescription.text = result.data.description
                }
                is Resource.Failure -> {
                    binding.groupData.isVisible = false
                    binding.tvError.isVisible = true
                    binding.progressBar.isVisible = false

                    binding.tvError.text = result.message
                }
                is Resource.Loading -> {
                    binding.groupData.isVisible = false
                    binding.tvError.isVisible = false
                    binding.progressBar.isVisible = true
                }
            }
        })
    }

    private fun showDayForecast() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fc_home_test, DayForecastFragment(viewModel.defaultCity.value.toString()))
            .addToBackStack(DayForecastFragment.DAY_TAG)
            .commit()
    }

    private fun showWeekForecast() {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.fc_home_test,
                WeekForecastFragment(viewModel.defaultCity.value.toString())
            )
            .addToBackStack(WeekForecastFragment.WEEK_TAG)
            .commit()
    }
}