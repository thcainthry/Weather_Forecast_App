package com.example.weather_forecast_app.ui.selectCity

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_forecast_app.R
import com.example.weather_forecast_app.databinding.CityItemBinding
import com.example.weather_forecast_app.domain.models.CurrentWeather
import com.example.weather_forecast_app.domain.models.MainFive

class CityAdapter : RecyclerView.Adapter<CityAdapter.ViewHolder>(){


    var cities: List<CurrentWeather> = emptyList()
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
            with(holder.binding){
                cityName.text = city.name
                mainTempCity.text = city.main?.temp.toString()?.substring(0,2)
                lowTempCity.text = city.main?.tempMin.toString()?.substring(0,2)
                highTempCity.text= city.main?.tempMax.toString()?.substring(0,2)
                cityName.setOnClickListener {
                    val bundleCityName = bundleOf(Pair("city_name_data" , city.name.toString()))
                    holder.itemView.findNavController().navigate(
                        R.id.action_cityFragment_to_homeFragment,
                        bundleCityName
                    )
                }
            }




        }
    override fun getItemCount(): Int = cities.size



}