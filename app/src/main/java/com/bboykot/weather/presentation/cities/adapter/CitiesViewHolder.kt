package com.bboykot.weather.presentation.cities.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bboykot.weather.databinding.ItemCitiesBinding
import com.bboykot.weather.domain.models.CurrentForecast

class CitiesViewHolder(private val binding: ItemCitiesBinding, private val listener: Listener): RecyclerView.ViewHolder(binding.root) {

    fun bind(forecast: CurrentForecast){
        binding.ivDelete.setOnClickListener { listener.onClick(forecast, ACTION_DELETE) }
        binding.btnDayForecast.setOnClickListener { listener.onClick(forecast, ACTION_SHOW_DAY_FORECAST) }
        binding.btnWeekForecast.setOnClickListener { listener.onClick(forecast, ACTION_SHOW_WEEK_FORECAST) }
        binding.apply {
            tvCity.text = forecast.city
            tvTemp.text = forecast.temperature.toString()
            tvWind.text = forecast.wind.toString()
            tvDescription.text = forecast.description
        }
    }

    companion object{
        const val ACTION_DELETE = "action_delete"
        const val ACTION_SHOW_DAY_FORECAST = "action_show_day_forecast"
        const val ACTION_SHOW_WEEK_FORECAST = "action_show_week_forecast"
    }
}