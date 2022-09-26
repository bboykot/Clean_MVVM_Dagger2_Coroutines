package com.bboykot.weather.presentation.week.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bboykot.weather.databinding.ItemDayForecastBinding
import com.bboykot.weather.domain.models.DailyForecast

class WeekForecastViewHolder(private val binding: ItemDayForecastBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(dailyForecast: DailyForecast) {
        binding.tvTempDay.text = dailyForecast.dayTemperature.toString()
        binding.tvTempNight.text = dailyForecast.nightTemperature.toString()
        binding.tvWind.text = dailyForecast.wind.toString()
        binding.tvDescription.text = dailyForecast.description
        binding.tvTime.text = dailyForecast.time
    }
}