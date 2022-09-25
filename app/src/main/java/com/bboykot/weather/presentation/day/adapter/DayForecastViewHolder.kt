package com.bboykot.weather.presentation.day.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bboykot.weather.databinding.ItemHourForecastBinding
import com.bboykot.weather.domain.models.HourForecast

class DayForecastViewHolder(private val binding: ItemHourForecastBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(hourForecast: HourForecast){
        binding.tvTemp.text = hourForecast.temperature.toString()
        binding.tvWind.text = hourForecast.wind.toString()
        binding.tvDescription.text = hourForecast.description
        binding.tvTime.text = hourForecast.time
    }
}