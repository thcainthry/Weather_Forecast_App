package com.example.weather_forecast_app.home.addCity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather_forecast_app.databinding.CitySearchAddBinding
import com.example.weather_forecast_app.databinding.FragmentThreeHoursBinding
import com.example.weather_forecast_app.home.hourly.HourlyAdapter

class CityFragment : Fragment() {
    private lateinit var binding: CitySearchAddBinding
    private lateinit var adapter: CityAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CitySearchAddBinding.inflate(inflater,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cityList.layoutManager = LinearLayoutManager(activity)
        adapter = CityAdapter()
        binding.cityList.adapter = adapter


    }

}