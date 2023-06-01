package com.example.weather_forecast_app.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_forecast_app.domain.models.FiveDayForecast
import com.example.weather_forecast_app.domain.models.ForecastCity
import com.example.weather_forecast_app.domain.repo.CityRepository
import com.example.weather_forecast_app.domain.repo.FiveDayRepository
import com.example.weather_forecast_app.domain.repo.HomeRepo
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
            var isHome = true
            val appKey = "3fd109d206c33b68e4b21397d3cf9943"
            private val homeRepo = HomeRepo()
            val homeData = MutableLiveData<ForecastCity>()
            private val homeList = ArrayList<ForecastCity>()
            private val searchRes = ArrayList<ForecastCity>()
            val searchLiveData = MutableLiveData<List<ForecastCity>>()

    fun getCurrentWeather(q:String, appid: String){
        viewModelScope.launch {
            homeList.addAll(listOf(homeRepo.getCurrentWeatherForCity(q,appKey)))
            searchLiveData.value = homeList
        }
    }

    fun searchCity(q: String,appid: String){
        searchRes.clear()
        for (city in homeList){
            city.name?.let {
                if (it.startsWith(q,true)){
                    searchRes.add(city)
                }
            }
        }
        searchLiveData.value = searchRes
    }
    fun setIsHome(args: String) {
        isHome = (args != " ")
    }

}