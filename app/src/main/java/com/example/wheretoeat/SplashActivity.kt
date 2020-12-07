package com.example.wheretoeat

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.wheretoeat.Adapters.RestaurantAdapter
import com.example.wheretoeat.Database.Entities.RestaurantData
import com.example.wheretoeat.Database.ViewModels.RestaurantViewModel


open class SplashActivity : AppCompatActivity(), RestaurantAdapter.OnItemClickListener {

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
            var adapter = RestaurantAdapter(this,this)
            adapter.setData(resList)
            startActivity(Intent(this, MainActivity::class.java))
        })
    }
}
