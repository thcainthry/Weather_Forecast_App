package com.example.weather_forecast_app.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_forecast_app.domain.models.CurrentWeather
import com.example.weather_forecast_app.domain.models.FiveDayForecast
import com.example.weather_forecast_app.domain.models.Weather
import com.example.weather_forecast_app.domain.repo.CurrentWeatherRepo
import com.example.weather_forecast_app.domain.repo.FiveDayRepository
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val currentWeatherRepo = CurrentWeatherRepo()
    private val forecastRepo = FiveDayRepository()
    val weatherLiveData = MutableLiveData<List<CurrentWeather>>()
    val daysLiveData = MutableLiveData<List<FiveDayForecast>>()


    fun getCurrentWeather(q: String, appid: String, units: String) {
        viewModelScope.launch {
          try {
              val weatherData = currentWeatherRepo.getCurrentWeatherForCity(q,appid,units)
              weatherLiveData.value = listOf(weatherData)
          }catch (e: Exception){
              Log.e("Tag", "Error fetching weather data: ${e.message}", e)
          }
        }
    }

    fun getForecastData(q: String, appid: String){
        viewModelScope.launch {
                try {
                    val forecastData = forecastRepo.getForecastData(q,appid)
                    val forecastList = listOf(forecastData)
                    daysLiveData.value = forecastList
                }catch (e: Exception){
                    Log.e("Tag", "Error fetching weather data: ${e.message}")
                }
        }
    }

}