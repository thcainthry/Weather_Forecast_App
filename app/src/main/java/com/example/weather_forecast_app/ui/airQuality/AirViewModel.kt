package com.example.weather_forecast_app.ui.airQuality

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_forecast_app.domain.models.AQI
import com.example.weather_forecast_app.domain.repo.AQIRepo
import kotlinx.coroutines.launch

class AirViewModel: ViewModel() {

    var aqi: List<AQI> = emptyList()
        set(value) {
            field = value
        }

    private val aqiRepo = AQIRepo()
    val aqiLiveData = MutableLiveData<List<AQI>>()
    fun getAIRPollution(lat: Int, lon:Int){
        viewModelScope.launch {
            try {
                val latlon = aqiRepo.getAIRPollution(lat, lon)
                aqiLiveData.value = listOf(latlon)
            }catch (e: Exception){
                Log.e("Tag", "Error fetching weather data: ${e.message}", e)
            }
        }
    }
}