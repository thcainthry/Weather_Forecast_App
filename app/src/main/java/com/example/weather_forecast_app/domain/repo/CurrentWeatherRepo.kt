package com.example.weather_forecast_app.domain.repo

import com.example.weather_forecast_app.data.ApiService
import com.example.weather_forecast_app.domain.models.CurrentWeather
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CurrentWeatherRepo {
    private val apiService: ApiService

    init{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun getCurrentWeatherForCity(q: String):
            CurrentWeather = apiService.getCurrentWeatherForCity(q)
}