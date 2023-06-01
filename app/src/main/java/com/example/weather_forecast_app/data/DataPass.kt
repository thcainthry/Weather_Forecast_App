package com.example.weather_forecast_app.data

interface DataPass {
    fun onDataPass(cityName: String, mainTemp: String, lowTemp: String, highTemp: String)
}