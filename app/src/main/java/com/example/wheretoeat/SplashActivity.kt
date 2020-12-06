package com.example.wheretoeat

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.wheretoeat.Adapters.RestaurantAdapter
import com.example.wheretoeat.Database.Entities.RestaurantData
import com.example.wheretoeat.Database.ViewModels.RestaurantViewModel


open class SplashActivity : AppCompatActivity() {

    private lateinit var restaurantViewModel: RestaurantViewModel
    private val SPLASH_TIME_OUT:Long = 3000 // 1 sec
    private var list : ArrayList<RestaurantData>? = null
    companion object
    {
        var restaurantDataMemory = HashMap<String, List<RestaurantData>>()
        var num_current_page: Int = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        dataLoad()
    }
    private fun dataLoad()
    {
        restaurantViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)
        restaurantViewModel.getRestaurants.observe(this, {resList->
            var adapter = RestaurantAdapter()
            adapter.setData(resList)
            startActivity(Intent(this, MainActivity::class.java))
        })
    }
}
