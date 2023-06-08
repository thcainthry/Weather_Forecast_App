package com.example.weather_forecast_app.ui.selectCity

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_forecast_app.databinding.CitySearchAddBinding

class CityFragment : Fragment() {
    lateinit var binding: CitySearchAddBinding
    private val adapter = CityAdapter()
    private lateinit var layoutManager: LinearLayoutManager
    private val viewModel: CityViewModel by viewModels()



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
        with(binding) {
            binding.cityList.layoutManager = LinearLayoutManager(activity)
            binding.cityList.adapter = adapter
            cityList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                }
            })

            searchBar.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    val query = binding.searchBar.text.toString()
                    if (query.isNotEmpty()){
                        viewModel.getWeatherCity(query, "3fd109d206c33b68e4b21397d3cf9943","metric")
                    }
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })
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