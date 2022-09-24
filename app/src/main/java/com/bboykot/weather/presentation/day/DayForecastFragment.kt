package com.bboykot.weather.presentation.day

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bboykot.weather.R
import com.bboykot.weather.app.App
import com.bboykot.weather.databinding.FragmentDayForecastBinding
import com.bboykot.weather.domain.models.Resource
import com.bboykot.weather.presentation.common.ViewModelFactory
import com.bboykot.weather.presentation.day.adapter.DayForecastAdapter
import javax.inject.Inject

class DayForecastFragment : Fragment(R.layout.fragment_day_forecast) {
    private val binding by viewBinding(FragmentDayForecastBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<DayForecastViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as App).appComponent.injectDayForecastFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_day_forecast, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        binding.root.setOnClickListener { parentFragmentManager.popBackStack() }
        val city = arguments?.getString(ARGUMENT_CITY_KEY)
        Log.i("XXX", "onViewCreated: $city")

        binding.rvDayForecast.layoutManager = LinearLayoutManager(requireContext())

        viewModel.result.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Success -> {
                    binding.rvDayForecast.isVisible = true
                    binding.pbLoading.isVisible = false
                    binding.tvError.isVisible = false

                    binding.rvDayForecast.adapter = DayForecastAdapter(requireContext(), result.data)
                }
                is Resource.Failure -> {
                    binding.rvDayForecast.isVisible = false
                    binding.pbLoading.isVisible = false
                    binding.tvError.isVisible = true

                    binding.tvError.text = result.message
                }
                is Resource.Loading -> {
                    binding.rvDayForecast.isVisible = false
                    binding.pbLoading.isVisible = true
                    binding.tvError.isVisible = false
                }
            }
        })
    }

    companion object{
        const val ARGUMENT_CITY_KEY = "argument_city_key"
        operator fun invoke(city: String) = DayForecastFragment().apply {
            arguments = bundleOf(ARGUMENT_CITY_KEY to city)
        }
    }
}