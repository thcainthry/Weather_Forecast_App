package com.example.weather_forecast_app

import android.content.Intent
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
//        binding.addLocation.setOnClickListener{
//            Navigation.findNavController(this@MainActivity,
//            R.id.nav_host_fragment).navigate(R.id.action_action_fragment_three_to_cityFragment)
//        }
        binding.more.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT,"")
            intent.type = "text/plain"
            val shareIntent = Intent.createChooser(intent,null)
            startActivity(shareIntent)
        }
    }
}