package com.bboykot.weather.presentation.cities.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bboykot.weather.databinding.ItemCitiesBinding
import com.bboykot.weather.domain.models.CurrentForecast

class CitiesViewHolder(private val binding: ItemCitiesBinding, private val listener: Listener): RecyclerView.ViewHolder(binding.root) {

    fun bind(forecast: CurrentForecast){
        binding.root.setOnClickListener { listener.onClick(forecast) }
        binding.apply {
            tvCity.text = forecast.city
            tvTemp.text = forecast.temperature.toString()
            tvWind.text = forecast.wind.toString()
            tvDescription.text = forecast.description
        }
    }
}