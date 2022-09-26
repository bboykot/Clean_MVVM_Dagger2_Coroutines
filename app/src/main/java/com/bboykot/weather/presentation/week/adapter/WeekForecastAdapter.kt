package com.bboykot.weather.presentation.week.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bboykot.weather.databinding.ItemDayForecastBinding
import com.bboykot.weather.domain.models.DailyForecast

class WeekForecastAdapter(
    private val context: Context,
    private val weekForecast: List<DailyForecast>,
) :
    RecyclerView.Adapter<WeekForecastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekForecastViewHolder {
        val binding = ItemDayForecastBinding.inflate(LayoutInflater.from(context), parent, false)
        return WeekForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeekForecastViewHolder, position: Int) {
        val dailyForecast = weekForecast[position]
        holder.bind(dailyForecast)
    }

    override fun getItemCount() = weekForecast.size
}