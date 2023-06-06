package com.example.weather_forecast_app

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.weather_forecast_app.databinding.ActivityMainBinding
import com.example.weather_forecast_app.ui.home.HomeFragment

class MainActivity : AppCompatActivity(), HomeFragment.CityNameListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)





        binding.addLocation.setOnClickListener{
            Navigation.findNavController(this@MainActivity,
            R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_cityFragment)
        }

        binding.more.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT,"")
            intent.type = "text/plain"
            val shareIntent = Intent.createChooser(intent,null)
            startActivity(shareIntent)
        }
    }

    override fun onCityNameEntered(cityName: String) {
        val sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("CITY_NAME",cityName)
        editor.apply()

        val data = sharedPref.getString("CITY_NAME","")
        binding.cityLocation.text = data

    }

}