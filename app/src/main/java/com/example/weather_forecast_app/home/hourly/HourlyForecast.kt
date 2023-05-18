package com.example.weather_forecast_app.home.hourly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather_forecast_app.databinding.HourForecast24Binding

class HourlyForecast: Fragment() {
    lateinit var binding: HourForecast24Binding
    lateinit var adapter: HourlyForecastAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HourForecast24Binding.inflate(inflater,
        container,
        false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.hour24RecycleList.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        adapter = HourlyForecastAdapter()
        binding.hour24RecycleList.adapter = adapter
    }

}