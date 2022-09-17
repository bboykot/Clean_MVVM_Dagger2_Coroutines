package com.bboykot.weather.presentation.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bboykot.weather.R
import com.bboykot.weather.databinding.FragmentSearchBinding
import com.bboykot.weather.domain.models.CurrentForecast
import com.bboykot.weather.domain.models.Resource
import com.bboykot.weather.presentation.common.MainActivity
import com.bboykot.weather.presentation.common.ViewModelFactory
import javax.inject.Inject

class SearchFragment : Fragment(R.layout.fragment_search) {
    private val binding by viewBinding(FragmentSearchBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<SearchViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).searchSubcomponent.injectSearchFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnSearch.setOnClickListener {
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)

            viewModel.startSearch(binding.etSearch.text.toString())
        }

        binding.btnSave.setOnClickListener { viewModel.saveCity() }
        binding.btnSaveAsDefault.setOnClickListener { viewModel.saveCityAsDefault() }

        viewModel.result.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Success<CurrentForecast> -> {
                    binding.progressBar.isVisible = false
                    binding.tvError.isVisible = false
                    binding.groupData.isVisible = true

                    binding.tvCity.text = result.data.city
                    binding.tvTemp.text = result.data.temperature.toString()
                    binding.tvWind.text = result.data.wind.toString()
                    binding.tvDescription.text = result.data.description
                }
                is Resource.Failure -> {
                    binding.tvError.isVisible = true
                    binding.progressBar.isVisible = false
                    binding.groupData.isVisible = false

                    binding.tvError.text = result.message
                }
                is Resource.Loading -> {
                    binding.progressBar.isVisible = true
                    binding.tvError.isVisible = false
                    binding.groupData.isVisible = false
                }
            }
        })
    }
}