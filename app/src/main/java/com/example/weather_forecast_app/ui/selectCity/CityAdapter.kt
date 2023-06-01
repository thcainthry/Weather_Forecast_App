package com.example.weather_forecast_app.ui.selectCity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weather_forecast_app.databinding.CityItemBinding
import com.example.weather_forecast_app.domain.models.ForecastCity
import com.example.weather_forecast_app.domain.models.MainFive


class CityAdapter : RecyclerView.Adapter<CityAdapter.ViewHolder>(){


    var cities: List<ForecastCity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var tempV = arrayListOf<MainFive>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    inner class ViewHolder(val binding: CityItemBinding):
                RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val binding = CityItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        )
        return ViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                val city = cities[position]
                val temp = tempV[position]
            with(holder.binding){
                cityName.text = city.name
                lowTempCity.text = temp.tempMin.toString()
                highTempCity.text = temp.tempMax.toString()
                mainTempCity.text = temp.temp.toString()


            }

    }
    override fun getItemCount() = cities.size
}