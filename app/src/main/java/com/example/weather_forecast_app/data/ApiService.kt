package com.example.weather_forecast_app.data

import com.example.weather_forecast_app.domain.models.*
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getCurrentWeatherForCity(
        @Query("q") q: String,
        @Query("appid") appid: String,
        @Query("units") units: String
    ): CurrentWeather
    @GET("forecast")
    suspend fun getForecastData(
        @Query("q") q: String,
        @Query("appid") appid: String
    ):FiveDayForecast

}