package com.example.weather_forecast_app.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_forecast_app.domain.models.*
import com.example.weather_forecast_app.domain.repo.AQIRepo
import com.example.weather_forecast_app.domain.repo.CurrentWeatherRepo
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val currentWeatherRepo = CurrentWeatherRepo()
    private val aqiRepo = AQIRepo()
    val weatherLiveData = MutableLiveData<List<CurrentWeather>>()
    val daysLiveData = MutableLiveData<List<FiveDayForecast>>()
    val aqiLiveData = MutableLiveData<List<AQI>>()

    private val cityRepo = CurrentWeatherRepo()
    val cityLiveData = MutableLiveData<List<CurrentWeather>>()

    fun getWeatherCity(q: String){
        viewModelScope.launch {
            try {
                val cityData = cityRepo.getCurrentWeatherForCity(q)
                cityLiveData.value = listOf(cityData)
            }catch (e: Exception){
                Log.e("Tag", "Error fetching weather data: ${e.message}", e)
            }
        }
    }


    fun getCurrentWeather(q: String) {
        viewModelScope.launch {
          try {
              val weatherData = currentWeatherRepo.getCurrentWeatherForCity(q)
              weatherLiveData.value = listOf(weatherData)
          }catch (e: Exception){
              Log.e("Tag", "Error fetching weather data: ${e.message}", e)
          }
        }
    }
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