package com.example.weather_forecast_app.domain.repo

import com.example.weather_forecast_app.data.ApiService
import com.example.weather_forecast_app.domain.models.FiveDayForecast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HourlyRepo {
    private val apiService: ApiService
    val apiKey = "3fd109d206c33b68e4b21397d3cf9943"
    val baseUrl = "https://api.openweathermap.org/data/2.5/"

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }


    suspend fun getHourly(dt: Int,temp: Double):
            List<FiveDayForecast> = apiService.getHourly(dt,temp)


}