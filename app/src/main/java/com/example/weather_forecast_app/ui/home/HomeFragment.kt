package com.example.weather_forecast_app.ui.home

import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_forecast_app.R
import android.content.SharedPreferences
import android.net.Uri
import com.example.weather_forecast_app.databinding.HomeFragmentBinding
import java.util.*

class HomeFragment : Fragment(){
    lateinit var binding: HomeFragmentBinding
    private val adapter = HomeAdapter()
    private val homeViewModel: HomeViewModel by viewModels()
    val args: HomeFragmentArgs by navArgs()
    private var isLayoutAdded = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(
            layoutInflater,
            container, false)

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cityNameData = args.cityNameData
        homeViewModel.getCurrentWeather(cityNameData.toString())
        observeViewModel()
        with(binding){
        binding.fiveRecycleList.layoutManager =  LinearLayoutManager(activity)
        binding.fiveRecycleList.adapter = adapter
        fiveRecycleList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })

            binding.more.setOnClickListener{
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_TEXT,"")
                intent.type = "text/plain"
                val shareIntent = Intent.createChooser(intent,null)
                startActivity(shareIntent)
            }
        binding.addLocation.setOnClickListener {
            findNavController().navigate((R.id.action_homeFragment_to_cityFragment))
        }
        binding.threeHour.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_action_fragment_three)
            }
        }

    }


    private fun observeViewModel(){
        homeViewModel.weatherLiveData.observe(viewLifecycleOwner){
            weatherList->
            adapter.current = weatherList
           if (weatherList.isNotEmpty()){
               val weatherData = weatherList[0]
               val sunrise = weatherData.sys?.sunrise?:0
               val sunset = weatherData.sys?.sunset?:0
               val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
               val moreD = weatherData.id.toString()
               val lat = weatherData.coord?.lat?.toInt()
               val lon = weatherData.coord?.lon?.toInt()

               if (lat != null && lon != null) {
                   homeViewModel.getAIRPollution(lat,lon)
               }

               binding.mainTemp.text = weatherData.main?.temp.toString().substring(0,2)
               binding.highTemp.text = weatherData.main?.tempMax.toString().substring(0,2)
               binding.lowTemp.text = weatherData.main?.tempMin.toString().substring(0,2)
               binding.cityHome.text = weatherData.name.toString()
               binding.pressureNr.text = weatherData.main?.pressure.toString()
               binding.humidityNr.text = weatherData.main?.humidity.toString()
               binding.windSpeedD.text = weatherData.wind?.speed.toString()
               binding.sunrise.text = timeFormat.format(Date(sunrise*1000L)).toString()
               binding.sunset.text =  timeFormat.format(Date(sunset*1000L)).toString()
               binding.weatherCondition.text = weatherData.weather[0].description.toString()
               binding.cityLocation.text = weatherData.name.toString()

               binding.moreDetails.setOnClickListener {
                   val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://openweathermap.org/city/$moreD"))
                   startActivity(browserIntent)
               }

               when(weatherData.weather[0].main){
                   "Thunderstorm" -> {
                       binding.root.setBackgroundResource(R.drawable.thunderstorm)
                   }
                   "Drizzle" -> {
                       binding.root.setBackgroundResource(R.drawable.drizzle)
                   }
                   "Rain" -> {
                       binding.root.setBackgroundResource(R.drawable.rain)
                   }
                   "Snow" -> {
                       binding.root.setBackgroundResource(R.drawable.snow)
                   }
                   "Clear" -> {
                       binding.root.setBackgroundResource(R.drawable.clear)
                   }
                   "Clouds" -> {
                       binding.root.setBackgroundResource(R.drawable.background)
                   }
                   "Mist" -> {
                       binding.root.setBackgroundResource(R.drawable.mist)
                   }
                   "Smoke" -> {
                       binding.root.setBackgroundResource(R.drawable.smoke)
                   }
                   "Haze" -> {
                       binding.root.setBackgroundResource(R.drawable.haze)
                   }
                   "Fog" -> {
                       binding.root.setBackgroundResource(R.drawable.fog)
                   }
                   "Dust" -> {
                       binding.root.setBackgroundResource(R.drawable.dust)
                   }
                   "Sand" -> {
                       binding.root.setBackgroundResource(R.drawable.sand)
                   }
                   "Ash" -> {
                       binding.root.setBackgroundResource(R.drawable.ash)
                   }
                   "Squall" -> {
                       binding.root.setBackgroundResource(R.drawable.squall)
                   }
                   "Tornado" -> {
                       binding.root.setBackgroundResource(R.drawable.tornado)
                   }
               }
           }
        }

        homeViewModel.aqiLiveData.observe(viewLifecycleOwner){
            aqiList ->
                adapter.aqi = aqiList
                adapter.notifyDataSetChanged()
            if (aqiList.isNotEmpty()){
                val aqiData = aqiList[0]
                binding.aqiNr.text = aqiData.list[0].main?.aqi.toString()
            }
        }

        homeViewModel.daysLiveData.observe(viewLifecycleOwner){
            forecastList ->
                adapter.days = forecastList
                adapter.notifyDataSetChanged()
                if (forecastList.isNotEmpty() && !isLayoutAdded && forecastList!=null){
                    isLayoutAdded = true
                    for (i in 0 until forecastList.size){
                        val weatherForecast = forecastList[i]
                        binding.fiveRecycleList.getChildAt(i).findViewById<TextView>(R.id.day).text = weatherForecast.dtTxt.toString()
                        binding.fiveRecycleList.getChildAt(i).findViewById<TextView>(R.id.weather_condition_day).text = weatherForecast.weatherFive.firstOrNull()?.description
                        binding.fiveRecycleList.getChildAt(i).findViewById<TextView>(R.id.high_temp_day).text = weatherForecast.mainFive?.tempMax.toString()
                        binding.fiveRecycleList.getChildAt(i).findViewById<TextView>(R.id.low_temp_day).text = weatherForecast.mainFive?.tempMin.toString()

                    }
                }
        }

    }

}


