package com.bboykot.weather.presentation.week

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bboykot.weather.R
import com.bboykot.weather.app.App
import com.bboykot.weather.databinding.FragmentWeekForecastBinding
import com.bboykot.weather.presentation.common.ViewModelFactory
import javax.inject.Inject

class WeekForecastFragment : Fragment(R.layout.fragment_week_forecast) {
    private val binding by viewBinding(FragmentWeekForecastBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<WeekForecastViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as App).appComponent.getDayForecastComponent().extras(arguments)
            .buildForecastComp().injectWeekForecastViewModel(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("XXX", "onViewCreated: $viewModel")
    }
}