package com.example.weather_forecast_app.ui.hourly

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_forecast_app.domain.models.ForecastCity
import com.example.weather_forecast_app.domain.repo.HourlyRepo
import com.google.gson.Gson
import kotlinx.coroutines.launch

class HourlyViewModel:ViewModel() {
        private val repo = HourlyRepo()
        val hourlyLiveData = MutableLiveData<List<ForecastCity>>()
        private val hourlyList = ArrayList<ForecastCity>()

    fun getHourly(dt: Int,temp: Double){
        viewModelScope.launch {
            hourlyList.addAll(repo.getHourly(dt,temp))
            hourlyLiveData.value = hourlyList
        }
    }


}