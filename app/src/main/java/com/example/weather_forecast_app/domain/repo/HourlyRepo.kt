package com.example.weather_forecast_app.domain.repo

import com.example.weather_forecast_app.data.ApiService
import com.example.weather_forecast_app.domain.models.ForecastCity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HourlyRepo {
    private val apiService: ApiService
    val apiKey: String = "3e8d60b00adaafe61410a45ff9550a81"

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun getHourly(dt: Int,temp: Double):
            List<ForecastCity> = apiService.getHourly(dt,temp)


}