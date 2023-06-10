package com.example.weather_forecast_app.ui.selectCity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_forecast_app.domain.models.CurrentWeather
import com.example.weather_forecast_app.domain.repo.CurrentWeatherRepo
import kotlinx.coroutines.launch

class CityViewModel: ViewModel() {


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


}