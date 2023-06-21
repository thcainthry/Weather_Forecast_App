package com.example.weather_forecast_app.domain.models

import com.google.gson.annotations.SerializedName

data class AQI(

    @SerializedName("coord" ) var coord : CoordA?          = CoordA(),
    @SerializedName("list"  ) var list  : ArrayList<ListA> = arrayListOf()

)
data class CoordA (

    @SerializedName("lon" ) var lon : Int? = null,
    @SerializedName("lat" ) var lat : Int? = null

)


data class MainA (

    @SerializedName("aqi" ) var aqi : Int? = null

)

data class Components (

    @SerializedName("co"    ) var co   : Double? = null,
    @SerializedName("no"    ) var no   : Double? = null,
    @SerializedName("no2"   ) var no2  : Double? = null,
    @SerializedName("o3"    ) var o3   : Double? = null,
    @SerializedName("so2"   ) var so2  : Double? = null,
    @SerializedName("pm2_5" ) var pm25 : Double? = null,
    @SerializedName("pm10"  ) var pm10 : Double? = null,
    @SerializedName("nh3"   ) var nh3  : Double? = null

)

data class ListA (

    @SerializedName("main"       ) var main       : MainA?       = MainA(),
    @SerializedName("components" ) var components : Components? = Components(),
    @SerializedName("dt"         ) var dt         : Int?        = null

)