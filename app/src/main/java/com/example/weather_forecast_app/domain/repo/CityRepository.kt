package com.example.weather_forecast_app.domain.repo

import com.example.weather_forecast_app.data.ApiService
import com.example.weather_forecast_app.domain.models.*
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CityRepository{
        private val apiService: ApiService
        val apiKey = "3fd109d206c33b68e4b21397d3cf9943"


    init{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            apiService = retrofit.create(ApiService::class.java)
        }

    suspend fun getCurrentWeatherForCity(q: String):
            ForecastCity = apiService.getCurrentWeatherForCity(q,apiKey)
    suspend fun getCity(page: Int) : ForecastCity =
            apiService.getCity(page)

//    suspend fun getTemp(temp:Double, tempMin:Double,tempMax:Double, humidity:Int):
//            Main = apiService.getTemp(temp,tempMin,tempMax,humidity)
//
//    suspend fun sunRS(sunrise:Int, sunset:Int):
//            Sys = apiService.sunRS(sunrise,sunset)
//
//    suspend fun windSpeed(speed:Double, deg: Int):
//            Wind = apiService.windSpeed(speed,deg)
//
//    suspend fun otherInfo(feelsLike: Double, humidity: Int,pressure: Int):
//            Main = apiService.otherInfo(feelsLike,humidity,pressure)
//
//    suspend fun getVisibility(visibility: Int):
//            ForecastCity = apiService.getVisibility(visibility)



}