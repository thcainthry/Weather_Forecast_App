package com.example.weather_forecast_app.ui.hourly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather_forecast_app.databinding.FragmentThreeHoursBinding

class HourlyFragment: Fragment() {
    private lateinit var binding: FragmentThreeHoursBinding
    private lateinit var adapter: HourlyAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThreeHoursBinding.inflate(inflater,
        container,
        false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.hour24RecycleList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        adapter = HourlyAdapter()
        binding.hour24RecycleList.adapter = adapter


    }

}