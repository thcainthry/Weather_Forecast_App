package com.example.weather_forecast_app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather_forecast_app.R
import com.example.weather_forecast_app.databinding.HomeFragmentBinding
import com.example.weather_forecast_app.home.HomeFragmentArgs
import com.example.weather_forecast_app.ui.selectCity.CityAdapter

class HomeFragment : Fragment() {

    lateinit var binding: HomeFragmentBinding
    private val adapter = CityAdapter()
    lateinit var layoutManager: LinearLayoutManager
    private val viewModel: HomeViewModel by viewModels()
    private val args: HomeFragmentArgs by navArgs()


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
        args.city?.let {
            viewModel.setIsHome(it)
        }
        if (viewModel.isHome){
            args.city?.let { viewModel. }
        }



        binding.threeHour.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_action_fragment_three)
        }


    }

}


