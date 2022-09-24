package com.bboykot.weather.presentation.day.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bboykot.weather.databinding.ItemHourForecastBinding
import com.bboykot.weather.domain.models.HourForecast

class DayForecastAdapter(
    private val context: Context,
    private val dayForecast: List<HourForecast>,
) :
    RecyclerView.Adapter<DayForecastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayForecastViewHolder {
        val binding = ItemHourForecastBinding.inflate(LayoutInflater.from(context), parent, false)
        return DayForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DayForecastViewHolder, position: Int) {
        val hourForecast = dayForecast[position]
        holder.bind(hourForecast)
    }

    override fun getItemCount() = dayForecast.size
}