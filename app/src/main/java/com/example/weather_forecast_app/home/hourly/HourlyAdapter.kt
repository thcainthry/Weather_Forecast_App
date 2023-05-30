package com.example.weather_forecast_app.home.hourly

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_forecast_app.databinding.HourlyForecastListItemBinding
import com.example.weather_forecast_app.domain.models.ListW


class HourlyAdapter:RecyclerView.Adapter<HourlyAdapter.ViewHolder>() {


    private var wind: List<ListW> = emptyList()
        set(value){
            field = value
            notifyDataSetChanged()
        }
    inner class ViewHolder(val binding:HourlyForecastListItemBinding):
     RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            HourlyAdapter.ViewHolder {
        val binding = HourlyForecastListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HourlyAdapter.ViewHolder, position: Int) {
//        with(holder.binding){
//            hourlyTemp.text = wind[position].main?.temp.toString()
//            windSpeed.text = wind[position].wind?.speed.toString()
//            time.text = wind[position].dt.toString()
//
//        }
    }


    override fun getItemCount() = 10



}