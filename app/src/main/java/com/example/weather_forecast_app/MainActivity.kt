package com.example.weather_forecast_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.weather_forecast_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var binding: ActivityMainBinding
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addLocation.setOnClickListener{
            Navigation.findNavController(this@MainActivity,
            R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_cityFragment)
        }
    }
}