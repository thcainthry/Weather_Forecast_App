import com.google.gson.annotations.SerializedName

data class FiveDayForecast(

        @SerializedName("cod"     ) var cod     : String?         = null,
        @SerializedName("message" ) var message : Int?            = null,
        @SerializedName("cnt"     ) var cnt     : Int?            = null,
        @SerializedName("list"    ) var list    : ArrayList<ListF> = arrayListOf(),
        @SerializedName("city"    ) var city    : City?           = City()

)


data class MainF (

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

data class WeatherF (

        @SerializedName("id"          ) var id          : Int?    = null,
        @SerializedName("main"        ) var main        : String? = null,
        @SerializedName("description" ) var description : String? = null,
        @SerializedName("icon"        ) var icon        : String? = null

)
data class CloudsF (

        @SerializedName("all" ) var all : Int? = null

)
data class WindF (

        @SerializedName("speed" ) var speed : Double? = null,
        @SerializedName("deg"   ) var deg   : Int?    = null,
        @SerializedName("gust"  ) var gust  : Double? = null

)
data class RainF (

        @SerializedName("3h" ) var threeh : Double? = null

)
data class SysF (

        @SerializedName("pod" ) var pod : String? = null

)

data class ListF (

        @SerializedName("dt"         ) var dt         : Int?               = null,
        @SerializedName("main"       ) var main       : MainF?              = MainF(),
        @SerializedName("weather"    ) var weather    : ArrayList<WeatherF> = arrayListOf(),
        @SerializedName("clouds"     ) var clouds     : CloudsF?            = CloudsF(),
        @SerializedName("wind"       ) var wind       : WindF?              = WindF(),
        @SerializedName("visibility" ) var visibility : Int?               = null,
        @SerializedName("pop"        ) var pop        : Double?            = null,
        @SerializedName("rain"       ) var rain       : RainF?              = RainF(),
        @SerializedName("sys"        ) var sys        : SysF?               = SysF(),
        @SerializedName("dt_txt"     ) var dtTxt      : String?            = null

)
data class CoordF (

        @SerializedName("lat" ) var lat : Double? = null,
        @SerializedName("lon" ) var lon : Double? = null

)

data class City (

        @SerializedName("id"         ) var id         : Int?    = null,
        @SerializedName("name"       ) var name       : String? = null,
        @SerializedName("coord"      ) var coord      : CoordF?  = CoordF(),
        @SerializedName("country"    ) var country    : String? = null,
        @SerializedName("population" ) var population : Int?    = null,
        @SerializedName("timezone"   ) var timezone   : Int?    = null,
        @SerializedName("sunrise"    ) var sunrise    : Int?    = null,
        @SerializedName("sunset"     ) var sunset     : Int?    = null

)