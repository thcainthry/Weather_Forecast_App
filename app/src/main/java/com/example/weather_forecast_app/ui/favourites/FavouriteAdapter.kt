package com.example.weather_forecast_app.ui.favourites

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_forecast_app.databinding.FavouriteItemBinding

class FavouriteAdapter(private val context: Context) : RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {

    private lateinit var sharedPreferences: SharedPreferences
    private var cities: List<String?> = emptyList()
    init{
        sharedPreferences = context.getSharedPreferences("Favourites", Context.MODE_PRIVATE)
    }

    fun setCities(cityList: List<String?>?){
        cities = cityList?: emptyList()
        notifyDataSetChanged()
    }
    inner class ViewHolder(val binding: FavouriteItemBinding ):
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
                val binding = FavouriteItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            return ViewHolder(binding)
            }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val city = cities[position]
            holder.binding.cityName.text = city
    }

    override fun getItemCount(): Int = cities.size
}