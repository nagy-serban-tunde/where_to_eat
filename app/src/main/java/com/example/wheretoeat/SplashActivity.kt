package com.example.wheretoeat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.wheretoeat.Adapters.RestaurantAdapter
import com.example.wheretoeat.Database.Entities.CountriesData
import com.example.wheretoeat.Database.Entities.RestaurantData
import com.example.wheretoeat.Database.ViewModels.CountriesViewModel
import com.example.wheretoeat.Database.ViewModels.RestaurantViewModel


open class SplashActivity : AppCompatActivity(), RestaurantAdapter.OnItemClickListener {

    private lateinit var restaurantViewModel: RestaurantViewModel
    private lateinit var countriesViewModel: CountriesViewModel
    companion object
    {
        var restaurantDataMemory = HashMap<String, List<RestaurantData>>()
        var countriesDataMemory = HashMap<String, ArrayList<String>>()
        var num_current_page: Int = 1
        var coutryType : String = "US"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        dataLoad()
    }
    private fun dataLoad()
    {
        countriesViewModel = ViewModelProvider(this).get(CountriesViewModel::class.java)
        countriesViewModel.getCountries.observe(this,{})

        restaurantViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)
        restaurantViewModel.getRestaurants.observe(this, {
            startActivity(Intent(this, MainActivity::class.java))
        })


    }
}
