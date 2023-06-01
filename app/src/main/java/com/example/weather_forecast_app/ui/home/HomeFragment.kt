package com.example.weather_forecast_app.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather_forecast_app.R
import com.example.weather_forecast_app.databinding.HomeFragmentBinding
import com.example.weather_forecast_app.domain.models.ForecastCity
import com.example.weather_forecast_app.domain.models.Main
import com.example.weather_forecast_app.domain.models.MainFive
import com.example.weather_forecast_app.domain.models.Weather

class HomeFragment : Fragment() {

    lateinit var binding: HomeFragmentBinding
    private val adapter = HomeAdapter()
    private val homeViewModel: HomeViewModel by viewModels()
    val args: HomeFragmentArgs by navArgs()
    val appid = "3fd109d206c33b68e4b21397d3cf9943"

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fiveRecycleList.layoutManager =  LinearLayoutManager(activity)
        binding.fiveRecycleList.adapter = adapter
        with(binding){

//            cityHome.text = tempCN.name
//            mainTemp.text = tempV.temp.toString()
//            weatherCondition.text = tempDes.description

//            searchBarHome.setOnEditorActionListener { _, actionId, _ ->
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    val query = binding.searchBarHome.text.toString().trim()
//                    homeViewModel.getCurrentWeather(query, appiKey)
//
//                    val inputMethodManager =
//                        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//                    inputMethodManager.hideSoftInputFromWindow(binding.searchBarHome.windowToken, 0)
//                    return@setOnEditorActionListener true
//                }
//                false
//            }
//            searchBarHome.doOnTextChanged{ text, start, before, count ->
//                homeViewModel.searchCity(text.toString(),appid)

//            }
        }


        binding.threeHour.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_action_fragment_three)
        }


    }

}


