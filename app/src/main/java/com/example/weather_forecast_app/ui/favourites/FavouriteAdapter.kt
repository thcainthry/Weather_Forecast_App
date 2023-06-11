package com.example.weather_forecast_app.ui.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_forecast_app.databinding.FavouriteItemBinding

class FavouriteAdapter : RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {


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

    }

    override fun getItemCount(): Int = 10
}