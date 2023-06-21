package com.example.weather_forecast_app.ui.airQuality

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.weather_forecast_app.R
import com.example.weather_forecast_app.databinding.AirPollutionBinding

class AirQualityFragment: Fragment() {
    lateinit var binding: AirPollutionBinding
    private val airViewModel: AirViewModel by viewModels()
    val args: AirQualityFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AirPollutionBinding.inflate(
            layoutInflater,
            container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lat = args.lat?.toInt()
        val lon = args.lon?.toInt()
        if (lat != null && lon != null) {
            airViewModel.getAIRPollution(lat, lon)
        }
        observeViewModel()
        binding.goBackAir.setOnClickListener {
            findNavController().navigate(R.id.action_airQualityFragment_to_homeFragment)
        }
    }
    private fun observeViewModel(){
        airViewModel.aqiLiveData.observe(viewLifecycleOwner){
                aqiList ->
            airViewModel.aqi = aqiList
            if (aqiList.isNotEmpty()){
                val aqiData = aqiList[0]
                binding.coValue.text = aqiData.list[0].components?.co.toString()
                binding.noValue.text = aqiData.list[0].components?.no.toString()
                binding.no2Value.text = aqiData.list[0].components?.no2.toString()
                binding.o3Value.text = aqiData.list[0].components?.o3.toString()
                binding.so2Value.text = aqiData.list[0].components?.so2.toString()
                binding.pm25Value.text = aqiData.list[0].components?.pm25.toString()
                binding.pm10Value.text = aqiData.list[0].components?.pm10.toString()
                binding.nh3Value.text = aqiData.list[0].components?.nh3.toString()
                val qualityValue = aqiData.list[0].main?.aqi.toString()

                when(qualityValue){
                    "1" -> {
                        binding.coQuality.text = "Good"
                        binding.noQuality.text = "Good"
                        binding.no2Quality.text = "Good"
                        binding.o3Quality.text = "Good"
                        binding.so2Quality.text = "Good"
                        binding.pm25Quality.text = "Good"
                        binding.pm10Quality.text = "Good"
                        binding.nh3Quality.text = "Good"
                        binding.coQuality.setTextColor(resources.getColor( R.color.green))
                        binding.noQuality.setTextColor(resources.getColor( R.color.green))
                        binding.no2Quality.setTextColor(resources.getColor( R.color.green))
                        binding.o3Quality.setTextColor(resources.getColor( R.color.green))
                        binding.so2Quality.setTextColor(resources.getColor( R.color.green))
                        binding.pm25Quality.setTextColor(resources.getColor( R.color.green))
                        binding.pm10Quality.setTextColor(resources.getColor( R.color.green))
                        binding.nh3Quality.setTextColor(resources.getColor( R.color.green))

                    }
                    "2" -> {
                        binding.coQuality.text = "Fair"
                        binding.noQuality.text = "Fair"
                        binding.no2Quality.text = "Fair"
                        binding.o3Quality.text = "Fair"
                        binding.so2Quality.text = "Fair"
                        binding.pm25Quality.text = "Fair"
                        binding.pm10Quality.text = "Fair"
                        binding.nh3Quality.text = "Fair"
                        binding.coQuality.setTextColor(resources.getColor( R.color.yellow))
                        binding.noQuality.setTextColor(resources.getColor( R.color.yellow))
                        binding.no2Quality.setTextColor(resources.getColor( R.color.yellow))
                        binding.o3Quality.setTextColor(resources.getColor( R.color.yellow))
                        binding.so2Quality.setTextColor(resources.getColor( R.color.yellow))
                        binding.pm25Quality.setTextColor(resources.getColor( R.color.yellow))
                        binding.pm10Quality.setTextColor(resources.getColor( R.color.yellow))
                        binding.nh3Quality.setTextColor(resources.getColor( R.color.yellow))
                    }
                    "3" -> {
                        binding.coQuality.text = "Moderate"
                        binding.noQuality.text = "Moderate"
                        binding.no2Quality.text = "Moderate"
                        binding.o3Quality.text = "Moderate"
                        binding.so2Quality.text = "Moderate"
                        binding.pm25Quality.text = "Moderate"
                        binding.pm10Quality.text = "Moderate"
                        binding.nh3Quality.text = "Moderate"
                        binding.coQuality.setTextColor(resources.getColor( R.color.orange))
                        binding.noQuality.setTextColor(resources.getColor( R.color.orange))
                        binding.no2Quality.setTextColor(resources.getColor( R.color.orange))
                        binding.o3Quality.setTextColor(resources.getColor( R.color.orange))
                        binding.so2Quality.setTextColor(resources.getColor( R.color.orange))
                        binding.pm25Quality.setTextColor(resources.getColor( R.color.orange))
                        binding.pm10Quality.setTextColor(resources.getColor( R.color.orange))
                        binding.nh3Quality.setTextColor(resources.getColor( R.color.orange))
                    }
                    "4" -> {
                        binding.coQuality.text = "Poor"
                        binding.noQuality.text = "Poor"
                        binding.no2Quality.text = "Poor"
                        binding.o3Quality.text = "Poor"
                        binding.so2Quality.text = "Poor"
                        binding.pm25Quality.text = "Poor"
                        binding.pm10Quality.text = "Poor"
                        binding.nh3Quality.text = "Poor"
                        binding.coQuality.setTextColor(resources.getColor( R.color.red))
                        binding.noQuality.setTextColor(resources.getColor( R.color.red))
                        binding.no2Quality.setTextColor(resources.getColor( R.color.red))
                        binding.o3Quality.setTextColor(resources.getColor( R.color.red))
                        binding.so2Quality.setTextColor(resources.getColor( R.color.red))
                        binding.pm25Quality.setTextColor(resources.getColor( R.color.red))
                        binding.pm10Quality.setTextColor(resources.getColor( R.color.red))
                        binding.nh3Quality.setTextColor(resources.getColor( R.color.red))
                    }
                    "5" -> {
                        binding.coQuality.text = "Very Poor"
                        binding.noQuality.text = "Very Poor"
                        binding.no2Quality.text = "Very Poor"
                        binding.o3Quality.text = "Very Poor"
                        binding.so2Quality.text = "Very Poor"
                        binding.pm25Quality.text = "Very Poor"
                        binding.pm10Quality.text = "Very Poor"
                        binding.nh3Quality.text = "Very Poor"
                        binding.coQuality.setTextColor(resources.getColor( R.color.maroon))
                        binding.noQuality.setTextColor(resources.getColor( R.color.maroon))
                        binding.no2Quality.setTextColor(resources.getColor( R.color.maroon))
                        binding.o3Quality.setTextColor(resources.getColor( R.color.maroon))
                        binding.so2Quality.setTextColor(resources.getColor( R.color.maroon))
                        binding.pm25Quality.setTextColor(resources.getColor( R.color.maroon))
                        binding.pm10Quality.setTextColor(resources.getColor( R.color.maroon))
                        binding.nh3Quality.setTextColor(resources.getColor( R.color.maroon))
                    }
                }


            }
        }
    }




}