package com.bboykot.weather.presentation.cities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bboykot.weather.R
import com.bboykot.weather.databinding.FragmentCitiesBinding
import com.bboykot.weather.domain.models.CurrentForecast
import com.bboykot.weather.presentation.cities.adapter.CitiesAdapter
import com.bboykot.weather.presentation.cities.adapter.Listener
import com.bboykot.weather.presentation.common.MainActivity
import com.bboykot.weather.presentation.common.ViewModelFactory
import javax.inject.Inject

class CitiesFragment: Fragment(R.layout.fragment_cities), Listener {
    private val binding by viewBinding(FragmentCitiesBinding::bind)

    @Inject
    lateinit var viewModelFactory : ViewModelFactory
    private val viewModel by viewModels<CitiesViewModel> {viewModelFactory}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).citiesComponent.injectCitiesFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.rvCities.layoutManager = LinearLayoutManager(requireContext())
        val adapter = CitiesAdapter(requireContext(), listOf(CurrentForecast(1, "PPP", 22.2, 2.2, "")), this)
        binding.rvCities.adapter = adapter
    }

    override fun onClick(forecast: CurrentForecast) {
        Toast.makeText(requireContext(), forecast.city, Toast.LENGTH_LONG).show()
    }
}