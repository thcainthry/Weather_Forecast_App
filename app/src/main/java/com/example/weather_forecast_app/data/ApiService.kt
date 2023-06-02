package com.example.weather_forecast_app.data

import com.example.weather_forecast_app.domain.models.*
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getCurrentWeatherForCity(
        @Query("q") q: String,
        @Query("appid") appid: String
    ): ForecastCity
    @GET("weather")
    suspend fun getCity(
        @Query("id") id: Int
    ): ForecastCity

//    suspend fun getTemp(
//        @Query("temp") temp: Double,
//        @Query("temp_min") tempMin: Double,
//        @Query("temp_max") tempMax: Double,
//        @Query("humidity") humidity: Int
//    ): Main
//
//    @GET ("weather")
//    suspend fun sunRS(
//        @Query("sunrise") sunrise: Int,
//        @Query("sunset") sunset: Int
//    ): Sys
//    @GET ("weather")
//    suspend fun windSpeed(
//        @Query("speed") speed: Double,
//        @Query("deg") deg: Int
//    ): Wind
//
//    @GET ("weather")
//    suspend fun otherInfo(
//        @Query("feels_like") feelsLike: Double,
//        @Query("humidity") humidity: Int,
//        @Query("pressure") pressure: Int
//    ):Main
//    @GET ("weather")
//    suspend fun getVisibility(
//        @Query("visibility") visibility: Int
//    ): ForecastCity
//
    @GET ("weather")
    suspend fun getHourly(
        @Query("dt") dt: Int,
        @Query ("temp") temp: Double,
    ): List<ForecastCity>
}



