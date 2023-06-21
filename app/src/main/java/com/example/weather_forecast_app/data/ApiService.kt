package com.example.weather_forecast_app.data

import com.example.weather_forecast_app.domain.models.*
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getCurrentWeatherForCity(
        @Query("q") q: String,
        @Query("appid") appid: String = "3fd109d206c33b68e4b21397d3cf9943",
        @Query("units") units: String = "metric"
    ): CurrentWeather
    @GET("forecast")
    suspend fun getForecastData(
        @Query("q") q: String,
        @Query("appid") appid: String = "3fd109d206c33b68e4b21397d3cf9943"
    ):FiveDayForecast
    @GET ("forecast")
    suspend fun getHourly(
        @Query("dt") dt: Int,
        @Query ("temp") temp: Double,
    ): List<FiveDayForecast>

    @GET("air_pollution")
    suspend fun getAIRPollution(
        @Query("lat") lat: Int,
        @Query("lon") lon: Int,
        @Query("appid") appid: String = "3fd109d206c33b68e4b21397d3cf9943"
    ): AQI
}