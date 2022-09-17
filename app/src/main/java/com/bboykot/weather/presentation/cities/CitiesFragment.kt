package com.bboykot.weather.presentation.cities

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bboykot.weather.R
import com.bboykot.weather.databinding.FragmentCitiesBinding
import com.bboykot.weather.domain.models.CurrentForecast
import com.bboykot.weather.presentation.cities.adapter.CitiesAdapter

class CitiesFragment: Fragment(R.layout.fragment_cities) {
    private val binding by viewBinding(FragmentCitiesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.rvCities.layoutManager = LinearLayoutManager(requireContext())
        val adapter = CitiesAdapter(requireContext(), listOf(CurrentForecast(1, "PPP", 22.2, 2.2, "")))
        binding.rvCities.adapter = adapter
    }
}