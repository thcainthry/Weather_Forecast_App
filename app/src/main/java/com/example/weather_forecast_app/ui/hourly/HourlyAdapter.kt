package com.example.weather_forecast_app.ui.hourly
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_forecast_app.databinding.HourlyForecastListItemBinding
import com.example.weather_forecast_app.domain.models.*


class HourlyAdapter:RecyclerView.Adapter<HourlyAdapter.ViewHolder>() {


    private var wind: List<Wind> = emptyList()
        set(value){
            field = value
            notifyDataSetChanged()
        }
    private var hour: List<FiveDayForecast> = emptyList()
        set(value){
            field = value
            notifyDataSetChanged()
        }
    private var temp: List<Main> = emptyList()
        set(value){
            field = value
            notifyDataSetChanged()
        }
    inner class ViewHolder(val binding:HourlyForecastListItemBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val binding = HourlyForecastListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding){
            hourlyTemp.text = temp[position].temp.toString()
            windSpeed.text = wind[position].speed.toString()
            time.text = hour[position].dt.toString()
        }
    }


    override fun getItemCount() = hour.size



}