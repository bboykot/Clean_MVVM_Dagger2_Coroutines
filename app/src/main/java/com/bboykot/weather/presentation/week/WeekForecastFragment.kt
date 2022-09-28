package com.bboykot.weather.presentation.week

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bboykot.weather.R
import com.bboykot.weather.app.callAppComponent
import com.bboykot.weather.databinding.FragmentWeekForecastBinding
import com.bboykot.weather.domain.models.Resource
import com.bboykot.weather.presentation.common.ViewModelFactory
import com.bboykot.weather.presentation.week.adapter.WeekForecastAdapter
import javax.inject.Inject

class WeekForecastFragment : Fragment(R.layout.fragment_week_forecast) {
    private val binding by viewBinding(FragmentWeekForecastBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<WeekForecastViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callAppComponent().getForecastComponent().extras(arguments)
            .buildForecastComp().injectWeekForecastViewModel(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.apply {
            setNavigationOnClickListener { parentFragmentManager.popBackStack() }
            title = arguments?.getString(ARGUMENT_CITY_KEY)
        }

        binding.rvWeekForecast.layoutManager = LinearLayoutManager(requireContext())

        viewModel.result.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Success -> {
                    binding.rvWeekForecast.isVisible = true
                    binding.pbLoading.isVisible = false
                    binding.tvError.isVisible = false

                    binding.rvWeekForecast.adapter =
                        WeekForecastAdapter(requireContext(), result.data)
                }
                is Resource.Failure -> {
                    binding.rvWeekForecast.isVisible = false
                    binding.pbLoading.isVisible = false
                    binding.tvError.isVisible = true

                    binding.tvError.text = result.message
                }
                is Resource.Loading -> {
                    binding.rvWeekForecast.isVisible = false
                    binding.pbLoading.isVisible = true
                    binding.tvError.isVisible = false
                }
            }
        })
    }

    companion object {
        const val WEEK_TAG = "week_tag"
        const val ARGUMENT_CITY_KEY = "argument_city_key"

        operator fun invoke(city: String) = WeekForecastFragment().apply {
            arguments = bundleOf(ARGUMENT_CITY_KEY to city)
        }
    }
}