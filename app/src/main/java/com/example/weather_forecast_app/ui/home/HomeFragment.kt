package com.example.weather_forecast_app.ui.home

import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_forecast_app.MainActivity
import com.example.weather_forecast_app.R
import android.content.SharedPreferences
import com.example.weather_forecast_app.databinding.HomeFragmentBinding
import com.example.weather_forecast_app.domain.models.FiveDayForecast
import java.util.*

class HomeFragment : Fragment() {
    interface CityNameListener{
        fun onCityNameEntered(cityName: String)
    }
    private var cityNameListener: CityNameListener? = null


    lateinit var binding: HomeFragmentBinding
    private val adapter = HomeAdapter()
    private val homeViewModel: HomeViewModel by viewModels()
    val args: HomeFragmentArgs by navArgs()
    private var isLayoutAdded = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(
            layoutInflater,
            container, false)

        return binding.root

    }

    override fun onAttach(context: Context){
        super.onAttach(context)
        cityNameListener = context as? CityNameListener
    }

    override fun onDetach() {
        super.onDetach()
        cityNameListener = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        with(binding){
        binding.fiveRecycleList.layoutManager =  LinearLayoutManager(activity)
        binding.fiveRecycleList.adapter = adapter
        fiveRecycleList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })

            searchBarHome.setOnEditorActionListener { _, actionId, _ ->
                searchBarHome.visibility = View.VISIBLE
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    var query = binding.searchBarHome.text.toString().trim()
                    homeViewModel.getCurrentWeather(query, "3fd109d206c33b68e4b21397d3cf9943","metric")
                    homeViewModel.getForecastData(query,"3fd109d206c33b68e4b21397d3cf9943")
                    val inputMethodManager =
                        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(binding.searchBarHome.windowToken, 0)
                    return@setOnEditorActionListener true
                }
                false

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

               binding.mainTemp.text = weatherData.main?.temp.toString()?.substring(0,2)
               binding.highTemp.text = weatherData.main?.tempMax.toString()?.substring(0,2)
               binding.lowTemp.text = weatherData.main?.tempMin.toString()?.substring(0,2)
               binding.cityHome.text = weatherData.name.toString()
               binding.pressureNr.text = weatherData.main?.pressure.toString()
               binding.humidityNr.text = weatherData.main?.humidity.toString()
               binding.windSpeedD.text = weatherData.wind?.speed.toString()
               binding.sunrise.text = timeFormat.format(Date(sunrise*1000L)).toString()
               binding.sunset.text =  timeFormat.format(Date(sunset*1000L)).toString()
               binding.weatherCondition.text = weatherData.weather[0].description.toString()
               saveData(weatherData.wind?.speed.toString())
               cityNameListener?.onCityNameEntered(cityName = weatherData.name.toString())


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
    private fun saveData(data: String){
        val sharedPref: SharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString("CITY_NAME",data)
        editor.apply()
    }

}


