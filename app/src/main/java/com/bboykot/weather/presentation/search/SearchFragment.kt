package com.bboykot.weather.presentation.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
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
        binding.tvTitle.text = "123"


        Log.i("XXX", "$viewModel ")

        binding.btnSearch.setOnClickListener {
            viewModel.startSearch(binding.etSearch.text.toString())
        }

        viewModel.searchResult.observe(viewLifecycleOwner, Observer { result ->
            binding.tvSearchResult.text = result
        })
    }
}