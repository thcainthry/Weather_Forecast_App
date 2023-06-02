package com.example.weather_forecast_app.ui.selectCity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_forecast_app.domain.models.ForecastCity
import com.example.weather_forecast_app.domain.repo.CityRepository
import kotlinx.coroutines.launch

class CityViewModel:ViewModel() {

    var currentPage = 1
    var isHome = true
    private val cityRepo = CityRepository()
    private val cityList = ArrayList<ForecastCity>()
    private val searchResults = ArrayList<ForecastCity>()
    val cityLiveData = MutableLiveData<List<ForecastCity>>()

    fun getCity(page: Int){
        viewModelScope.launch {
            cityList.addAll(listOf(cityRepo.getCity(page)))
            cityLiveData.value = cityList
        }
    }
    fun getCurrentWeatherForCity(q: String){
        viewModelScope.launch {
            cityList.addAll(listOf(cityRepo.getCurrentWeatherForCity(q)))
            cityLiveData.value = cityList
        }
    }

    fun loadMore(){
        currentPage++
        getCity(currentPage)
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

    fun setIsHome(args: String){
        isHome = (args !=" ")
    }
}