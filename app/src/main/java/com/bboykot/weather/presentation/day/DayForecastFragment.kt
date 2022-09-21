package com.bboykot.weather.presentation.day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bboykot.weather.R
import com.bboykot.weather.databinding.FragmentDayForecastBinding

class DayForecastFragment : Fragment(R.layout.fragment_day_forecast) {
    private val binding by viewBinding(FragmentDayForecastBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_day_forecast, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.root.setOnClickListener { parentFragmentManager.popBackStack() }
    }
}