package com.example.weather_forecast_app.data

import com.example.weather_forecast_app.domain.City
import com.example.weather_forecast_app.domain.Main
import com.example.weather_forecast_app.domain.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

        @GET ("weather")
        suspend fun getTemp(
            @Query("temp") temp:Double,
            @Query("temp_min") temp_min:Double,
            @Query("temp_max") temp_max:Double,
            @Query("humidity") humidity:Int
        ):Main

        @GET ("weather/{city}")
        suspend fun getWeatherOfCity(
            @Query("q") name: String

        //me ba nje liste me 5 6 qytete e caktume si dhe api key duhet me qene hard coded
        ):City

        @GET("weather")
        suspend fun getWeatherState(
            @Query("main") main: String,
            @Query("icon") icon: String
        ):Weather
}