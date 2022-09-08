package com.bboykot.weather.presentation.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bboykot.weather.R
import com.bboykot.weather.databinding.FragmentSearchBinding
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

        binding.btnSearch.setOnClickListener { viewModel.startSearch(binding.etSearch.text.toString()) }

        binding.btnSave.setOnClickListener { viewModel.saveCity() }

        viewModel.searchResult.observe(viewLifecycleOwner, Observer { result ->
            binding.tvCity.text = result.city
            binding.tvTemp.text = result.temperature.toString()
            binding.tvWind.text = result.wind.toString()
            binding.tvDescription.text = result.description
        })

        viewModel.requestError.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        })

        viewModel.progressVisibility.observe(viewLifecycleOwner, Observer { visibility ->
            binding.progressBar.isVisible = visibility
        })
    }
}