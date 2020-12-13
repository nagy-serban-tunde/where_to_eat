package com.example.wheretoeat.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wheretoeat.Database.Entities.RestaurantData
import com.example.wheretoeat.Database.ViewModels.CountriesViewModel
import com.example.wheretoeat.Database.ViewModels.RestaurantViewModel
import com.example.wheretoeat.MainActivity
import com.example.wheretoeat.R

open class SplashFragment : Fragment() {

    private lateinit var restaurantViewModel: RestaurantViewModel
    private lateinit var countriesViewModel: CountriesViewModel

    companion object
    {
        var restaurantDataMemory = HashMap<String, List<RestaurantData>>()
        var countriesDataMemory = HashMap<String, ArrayList<String>>()
        var num_current_page: Int = 1
        var countryType : String = "US"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_splash, container, false)
        dataLoad()
        return view
    }

    private fun dataLoad()
    {
        countriesViewModel = ViewModelProvider(requireActivity()).get(CountriesViewModel::class.java)
        countriesViewModel.getCountries.observe(viewLifecycleOwner,{})

        restaurantViewModel = ViewModelProvider(requireActivity()).get(RestaurantViewModel::class.java)
        restaurantViewModel.loadRestaurant()
        restaurantViewModel.restList.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.mainScreenFragment)
        })
    }

}