package com.example.weather_forecast_app.ui.selectCity


import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_forecast_app.R
import com.example.weather_forecast_app.databinding.CityItemBinding
import com.example.weather_forecast_app.domain.models.CurrentWeather


class CityAdapter(private val sharedPreferences: SharedPreferences) : RecyclerView.Adapter<CityAdapter.ViewHolder>(){



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
                mainTempCity.text = city.main?.temp.toString().substring(0,2)
                lowTempCity.text = city.main?.tempMin.toString().substring(0,2)
                highTempCity.text= city.main?.tempMax.toString().substring(0,2)

                val cityNameBoolean = city.name.toString()
                val isCityFavourite = isCityFavourite(cityNameBoolean)

                cityName.setOnClickListener {
                    val bundleCityName = bundleOf(Pair("city_name_data" , city.name.toString()))
                    holder.itemView.findNavController().navigate(
                        R.id.action_cityFragment_to_homeFragment,
                        bundleCityName
                    )
                }
                setFavourite.setOnClickListener {
                    val selectedCity = city.name.toString()
                    val sharedPrefsEditor = holder.itemView.context.getSharedPreferences("Favourites", Context.MODE_PRIVATE).edit()
                    val favouriteCities = sharedPreferences.getStringSet("cities", HashSet<String>())?.toMutableSet()
                    if (isCityFavourite){
                        favouriteCities?.remove(selectedCity)
                        setFavourite.setImageResource(R.drawable.set_fav)
                    }else{
                        favouriteCities?.add(selectedCity)
                        setFavourite.setImageResource(R.drawable.favorite)
                    }
                    sharedPrefsEditor?.putStringSet("cities",favouriteCities)
                    sharedPrefsEditor?.apply()
                }

                val selectedCity = city.name.toString()
                val isFavourite = isCityFavourite(selectedCity)

                if (isFavourite){
                    setFavourite.setImageResource(R.drawable.favorite)
                }else{
                    setFavourite.setImageResource(R.drawable.set_fav)
                }
            }




        }
    override fun getItemCount(): Int = cities.size

    private fun isCityFavourite(cityName: String): Boolean{
        val favouriteCities = sharedPreferences.getStringSet("cities", HashSet<String>())
        return favouriteCities?.contains(cityName) ?: false
    }



}