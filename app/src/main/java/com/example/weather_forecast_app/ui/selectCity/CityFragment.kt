package com.example.weather_forecast_app.ui.selectCity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AbsListView.OnScrollListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_forecast_app.data.DataPass
import com.example.weather_forecast_app.databinding.CitySearchAddBinding
import com.example.weather_forecast_app.domain.models.ForecastCity

class CityFragment : Fragment() {
    lateinit var binding: CitySearchAddBinding
    val adapter = CityAdapter(object : DataPass{
        override fun onDataPass(cityName: String, mainTemp: String, lowTemp: String, highTemp: String){}
    })
    private lateinit var layoutManager: LinearLayoutManager
    private val viewModel: CityViewModel by viewModels()
    val args: CityFragmentArgs by navArgs()
    val appiKey = "3fd109d206c33b68e4b21397d3cf9943"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CitySearchAddBinding.inflate(
            layoutInflater,
            container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        args.city?.let { viewModel.setIsHome(it) }
        if (viewModel.isHome){
            args.city?.let { viewModel.getCurrentWeatherForCity(it) }
        }



        with(binding){

            layoutManager = LinearLayoutManager(activity)
            cityList.adapter = adapter
            cityList.layoutManager = layoutManager
            searchBar.doOnTextChanged{text,start,before,count ->
                cityList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (!viewModel.isHome && searchBar.text.isEmpty() && layoutManager.findLastCompletelyVisibleItemPosition() > adapter.itemCount - 3) {
                            binding.loadMoreloader.visibility = View.VISIBLE
                            viewModel.loadMore()
                        }
                    }
                })
                viewModel.searchCity(text.toString())
            }
        }


    }

    private fun observeViewModel(){
        viewModel.cityLiveData.observe(viewLifecycleOwner){
            adapter.cities = it
            binding.loader.visibility = View.GONE
            binding.loadMoreloader.visibility = View.GONE
        }
    }







}

