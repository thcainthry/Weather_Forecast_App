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
import android.net.Uri
import android.widget.ImageView
import androidx.core.os.bundleOf
import com.example.weather_forecast_app.databinding.HomeFragmentBinding
import com.squareup.picasso.Picasso
import java.util.*

class HomeFragment : Fragment() {
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
            container, false
        )

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cityNameData = args.cityNameData
        val cityNameBundle = args.cityData
        val cityNameFav = args.cityNameFav

        if (!cityNameData.isNullOrEmpty() && !cityNameData.isNullOrBlank()) {
            homeViewModel.getCurrentWeather(cityNameData.toString())
            homeViewModel.getForecastData(cityNameData.toString())
        }

        if (!cityNameBundle.isNullOrEmpty() && !cityNameBundle.isNullOrBlank()) {
            homeViewModel.getCurrentWeather(cityNameBundle.toString())
            homeViewModel.getForecastData(cityNameBundle.toString())
        }

        if (!cityNameFav.isNullOrEmpty() && !cityNameFav.isNullOrBlank()) {
            homeViewModel.getCurrentWeather(cityNameFav.toString())
            homeViewModel.getForecastData(cityNameFav.toString())
        }

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
            findNavController().navigate(
                    R.id.action_homeFragment_to_cityFragment
            )
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
               val cityN = weatherData.name.toString()

               if (lat != null && lon != null) {
                   homeViewModel.getAIRPollution(lat,lon)
                   binding.moreDetailsAqi.setOnClickListener {
                       val bundleData = bundleOf(
                           Pair("lat",lat.toString()),
                           Pair("lon",lon.toString()),
                           Pair("city_data",cityN)
                       )
                       findNavController().navigate(
                           R.id.action_homeFragment_to_airQualityFragment,
                           bundleData
                       )
                   }
               }

               binding.mainTemp.text = weatherData.main?.temp.toString().substring(0,2)
               binding.highTemp.text = weatherData.main?.tempMax.toString().substring(0,2)
               binding.lowTemp.text = weatherData.main?.tempMin.toString().substring(0,2)
               binding.cityHome.text = cityN
               binding.pressureNr.text = weatherData.main?.pressure.toString()
               binding.humidityNr.text = weatherData.main?.humidity.toString()
               binding.windSpeedD.text = weatherData.wind?.speed.toString()
               binding.sunrise.text = timeFormat.format(Date(sunrise*1000L)).toString()
               binding.sunset.text =  timeFormat.format(Date(sunset*1000L)).toString()
               binding.weatherCondition.text = weatherData.weather[0].description.toString()
               binding.cityLocation.text = cityN

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

        homeViewModel.daysLiveData.observe(viewLifecycleOwner) {
            adapter.days = it
            adapter.notifyDataSetChanged()
        }


    }

}


