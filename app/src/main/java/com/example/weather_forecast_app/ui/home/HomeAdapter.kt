package com.example.weather_forecast_app.ui.home

import FiveDayForecast
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_forecast_app.databinding.ItemFiveDayBinding
import com.example.weather_forecast_app.domain.models.*
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

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
        val forecast = days[position]
        with(holder.binding) {
            day.text = ""
            weatherConditionDay.text = ""
            highTempDay.text = ""
            lowTempDay.text = ""

            for (i in 0 until forecast.list.size) {
                val dayData = forecast.list[i]
                val icon = dayData.weather[0].icon
                val iconUrl = "http://openweathermap.org/img/w/$icon.png"
                val date = formatDate(dayData.dtTxt.toString())
                day.append(date)
                weatherConditionDay.append(dayData.weather[0].description.toString())
                highTempDay.append(dayData.main?.tempMax.toString().substring(0, 2))
                lowTempDay.append(dayData.main?.tempMin.toString().substring(0, 2))

                Picasso.get().load(iconUrl).into(cloudOne)


                if (i < forecast.list.size - 1) {
                    day.append("\n")
                    weatherConditionDay.append("\n")
                    highTempDay.append("\n")
                    lowTempDay.append("\n")

                }
            }
        }
    }


    override fun getItemCount(): Int = days.size

    fun formatDate(dateString: String) :String{
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = dateFormat.parse(dateString)
        val dayFormat = SimpleDateFormat("EEE. HH:mm", Locale.getDefault())
        return  dayFormat.format(date)
    }
}