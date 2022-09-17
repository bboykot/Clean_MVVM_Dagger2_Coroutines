package com.bboykot.weather.presentation.cities.adapter

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.bboykot.weather.databinding.ItemCitiesBinding
import com.bboykot.weather.domain.models.CurrentForecast

class CitiesViewHolder(private val binding: ItemCitiesBinding): RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {  }
    }
    fun bind(forecast: CurrentForecast){
        binding.tvTest.text = forecast.city
    }
}