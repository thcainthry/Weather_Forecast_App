package com.example.weather_forecast_app.ui.selectCity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_forecast_app.domain.models.ForecastCity
import com.example.weather_forecast_app.domain.repo.CityRepository
import kotlinx.coroutines.launch

class CityViewModel:ViewModel() {
    private val repo = CityRepository()
    private val cityList = ArrayList<ForecastCity>()
    private val searchResults = ArrayList<ForecastCity>()
    private val cityLiveData = MutableLiveData<List<ForecastCity>>()

    fun getCurrentWeatherForCity(name: String){
        viewModelScope.launch {
            cityList.addAll(repo.getCurrentWeatherForCity(name))
            cityLiveData.value = cityList
        }
    }
    fun searchCity(query: String) {
        searchResults.clear()
        for (city in cityList) {
            city.name?.let {
                if (it.startsWith(query, true)) {
                    searchResults.add(city)
                }
            }
        }
        cityLiveData.value = searchResults
    }
}