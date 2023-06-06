package com.example.weather_forecast_app.domain.models

import com.google.gson.annotations.SerializedName

data class FiveDayForecast (
        @SerializedName("dt"         ) var dt         : Int?               = null,
        @SerializedName("main"       ) var mainFive       : MainFive?              = MainFive(),
        @SerializedName("weather"    ) var weatherFive    : ArrayList<WeatherFive> = arrayListOf(),
        @SerializedName("clouds"     ) var clouds     : Clouds?            = Clouds(),
        @SerializedName("wind"       ) var wind       : Wind?              = Wind(),
        @SerializedName("visibility" ) var visibility : Int?               = null,
        @SerializedName("pop"        ) var pop        : Int?               = null,
        @SerializedName("sys"        ) var sys        : Sys?               = Sys(),
        @SerializedName("dt_txt"     ) var dtTxt      : String?            = null
)
data class WeatherFive (

        @SerializedName("id"          ) var id          : Int?    = null,
        @SerializedName("main"        ) var main        : String? = null,
        @SerializedName("description" ) var description : String? = null,
        @SerializedName("icon"        ) var icon        : String? = null

)
data class MainFive (

        @SerializedName("temp"       ) var temp      : Double? = null,
        @SerializedName("feels_like" ) var feelsLike : Double? = null,
        @SerializedName("temp_min"   ) var tempMin   : Double? = null,
        @SerializedName("temp_max"   ) var tempMax   : Double? = null,
        @SerializedName("pressure"   ) var pressure  : Int?    = null,
        @SerializedName("sea_level"  ) var seaLevel  : Int?    = null,
        @SerializedName("grnd_level" ) var grndLevel : Int?    = null,
        @SerializedName("humidity"   ) var humidity  : Int?    = null,
        @SerializedName("temp_kf"    ) var tempKf    : Double? = null

)


