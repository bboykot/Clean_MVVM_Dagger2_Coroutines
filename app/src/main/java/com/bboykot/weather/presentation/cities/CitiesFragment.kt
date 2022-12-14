package com.bboykot.weather.presentation.cities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bboykot.weather.R
import com.bboykot.weather.app.callMainActivity
import com.bboykot.weather.databinding.FragmentCitiesBinding
import com.bboykot.weather.domain.models.CurrentForecast
import com.bboykot.weather.domain.models.Resource
import com.bboykot.weather.presentation.cities.adapter.CitiesAdapter
import com.bboykot.weather.presentation.cities.adapter.CitiesViewHolder
import com.bboykot.weather.presentation.cities.adapter.Listener
import com.bboykot.weather.presentation.common.ViewModelFactory
import com.bboykot.weather.presentation.day.DayForecastFragment
import com.bboykot.weather.presentation.week.WeekForecastFragment
import javax.inject.Inject

class CitiesFragment : Fragment(R.layout.fragment_cities), Listener {
    private val binding by viewBinding(FragmentCitiesBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<CitiesViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callMainActivity().citiesComponent.injectCitiesFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.rvCities.layoutManager = LinearLayoutManager(requireContext())

        viewModel.result.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Success -> {
                    binding.progressBar.isVisible = false
                    binding.rvCities.isVisible = true
                    binding.tvError.isVisible = false

                    val adapter = CitiesAdapter(requireContext(), result.data, this)
                    binding.rvCities.adapter = adapter
                }
                is Resource.Failure -> {
                    Log.i("XXX", "onViewCreated: ")
                    binding.progressBar.isVisible = false
                    binding.rvCities.isVisible = false
                    binding.tvError.isVisible = true

                    binding.tvError.text = result.message
                }
                is Resource.Loading -> {
                    binding.progressBar.isVisible = true
                    binding.rvCities.isVisible = false
                    binding.tvError.isVisible = false
                }
            }
        })
    }

    override fun onClick(forecast: CurrentForecast, action: String) {
        when (action) {
            CitiesViewHolder.ACTION_DELETE -> viewModel.deleteCity(forecast)
            CitiesViewHolder.ACTION_SHOW_DAY_FORECAST -> {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fc_container, DayForecastFragment(forecast.city)).addToBackStack(DayForecastFragment.DAY_TAG)
                    .commit()
            }
            CitiesViewHolder.ACTION_SHOW_WEEK_FORECAST -> {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fc_container, WeekForecastFragment(forecast.city)).addToBackStack(WeekForecastFragment.WEEK_TAG)
                    .commit()
            }
        }
    }
}