package com.example.weather_forecast_app.ui.selectCity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_forecast_app.R
import com.example.weather_forecast_app.databinding.CitySearchAddBinding
import com.example.weather_forecast_app.ui.home.HomeViewModel

class CityFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var binding: CitySearchAddBinding
    private lateinit var adapter: CityAdapter
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CitySearchAddBinding.inflate(
            layoutInflater,
            container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireContext().getSharedPreferences("Favourites", Context.MODE_PRIVATE)
        adapter = CityAdapter(sharedPreferences)
        observeViewModel()
        with(binding) {
            cityList.layoutManager = LinearLayoutManager(activity)
            cityList.adapter = adapter
            cityList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
            binding.favorite.setOnClickListener{
                findNavController().navigate(R.id.action_cityFragment_to_favouriteFragment)
            }
            binding.goBackHome.setOnClickListener {
                findNavController().navigate(
                    R.id.action_cityFragment_to_homeFragment,


                )
            }

            searchBar.setOnEditorActionListener { _, actionId, _ ->
                searchBar.visibility = View.VISIBLE
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    val query = binding.searchBar.text.toString().trim()
                    viewModel.getWeatherCity(query)
                    val inputMethodManager =
                        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(binding.searchBar.windowToken, 0)
                    return@setOnEditorActionListener true
                }
                false
            }
        }
    }
    private fun observeViewModel() {
        viewModel.cityLiveData.observe(viewLifecycleOwner) {
            adapter.cities = it
            binding.loader.visibility = View.GONE
            binding.loadMoreloader.visibility = View.GONE
        }
    }
}