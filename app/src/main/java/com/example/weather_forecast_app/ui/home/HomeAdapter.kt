package com.example.weather_forecast_app.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_forecast_app.databinding.ItemFiveDayBinding
import com.example.weather_forecast_app.domain.models.CurrentWeather
import com.example.weather_forecast_app.domain.models.FiveDayForecast
import com.example.weather_forecast_app.domain.models.MainFive
import com.example.weather_forecast_app.domain.models.WeatherFive

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
//        with(holder.binding){
//
//            day.text = days[position].dt.toString()
//            weatherConditionDay.text = otherInfoD[position].description
//            highTempDay.text = tempV[position].tempMax.toString()
//            lowTempDay.text = tempV[position].tempMin.toString()
//        }
    }

    override fun getItemCount(): Int = current.size
}