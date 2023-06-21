package com.example.weather_forecast_app.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_forecast_app.databinding.ItemFiveDayBinding
import com.example.weather_forecast_app.domain.models.*

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var current: List<CurrentWeather> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var days: List<FiveDayForecast> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var aqi: List<AQI> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    inner class ViewHolder(val binding:ItemFiveDayBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
       val binding = ItemFiveDayBinding.inflate(
           LayoutInflater.from(parent.context),
           parent,false
       )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val daysL = days[position]
//        with(holder.binding){
//            day.text = daysL.dtTxt.toString()
//            weatherConditionDay.text = daysL.weatherFive.firstOrNull()?.description
//            highTempDay.text = daysL.mainFive?.tempMax.toString()
//            lowTempDay.text = daysL.mainFive?.tempMin.toString()
//        }
    }

    override fun getItemCount(): Int = current.size
}