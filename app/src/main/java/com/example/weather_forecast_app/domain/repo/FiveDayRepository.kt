package com.example.weather_forecast_app.domain.repo

import FiveDayForecast
import com.example.weather_forecast_app.data.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FiveDayRepository {
    private val apiService: ApiService

    init{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun getForecastData(q: String):
            FiveDayForecast = apiService.getForecastData(q)
}