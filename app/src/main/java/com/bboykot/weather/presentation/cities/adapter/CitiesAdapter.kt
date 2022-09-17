package com.bboykot.weather.presentation.cities.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bboykot.weather.databinding.ItemCitiesBinding
import com.bboykot.weather.domain.models.CurrentForecast

class CitiesAdapter(private val context: Context, private val citiesForecasts: List<CurrentForecast>): RecyclerView.Adapter<CitiesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val binding = ItemCitiesBinding.inflate(LayoutInflater.from(context), parent, false)
        return CitiesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        val cityForecast = citiesForecasts[position]
        holder.bind(cityForecast)
    }

    override fun getItemCount() = citiesForecasts.size
}

