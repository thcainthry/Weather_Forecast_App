package com.example.weather_forecast_app.ui.selectCity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather_forecast_app.databinding.CitySearchAddBinding

class CityFragment : Fragment() {
    lateinit var binding: CitySearchAddBinding
    private val adapter = CityAdapter()
    private val cityViewModel: CityViewModel by viewModels()
    private val args: CityFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CitySearchAddBinding.inflate(inflater,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cityName = args.city
        cityViewModel.getCurrentWeatherForCity(cityName.toString())



    }

}