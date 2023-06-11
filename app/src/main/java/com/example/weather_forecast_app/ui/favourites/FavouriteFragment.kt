package com.example.weather_forecast_app.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather_forecast_app.R
import com.example.weather_forecast_app.databinding.FavouriteFragmentBBinding

class FavouriteFragment : Fragment() {
    lateinit var binding: FavouriteFragmentBBinding
    private val adapter = FavouriteAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FavouriteFragmentBBinding.inflate(
            layoutInflater,
            container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            binding.favList.layoutManager = LinearLayoutManager(activity)
            binding.favList.adapter = adapter

            binding.goBackCity.setOnClickListener{
                findNavController().navigate(R.id.action_favouriteFragment_to_cityFragment)
            }
            binding.goHome.setOnClickListener {
                findNavController().navigate(R.id.action_favouriteFragment_to_homeFragment)
            }
        }
    }

}