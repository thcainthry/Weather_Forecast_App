package com.example.weather_forecast_app.home.hourly

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weather_forecast_app.databinding.Hour24ForecastListItemBinding


class HourlyForecastAdapter:RecyclerView.Adapter<ViewHolder>() {
    inner class ViewHolder(binding: Hour24ForecastListItemBinding):
    RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
      val binding = Hour24ForecastListItemBinding.inflate(
          LayoutInflater.from(parent.context),
          parent, false
      )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 24
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}