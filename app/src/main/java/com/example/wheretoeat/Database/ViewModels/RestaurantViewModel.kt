package com.example.wheretoeat.Database.ViewModels

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wheretoeat.Database.Entities.RespData
import com.example.wheretoeat.Database.Entities.RestaurantData
import com.example.wheretoeat.Network.Api
import com.example.wheretoeat.SplashActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log


class RestaurantViewModel : ViewModel() {

    private var restList: MutableLiveData<List<RestaurantData>>? = null
    val getRestaurants: MutableLiveData<List<RestaurantData>>
        get() {
            if (restList == null) {
                restList = MutableLiveData<List<RestaurantData>>()
                loadRestaurant()
            }
            return restList as MutableLiveData<List<RestaurantData>>
        }

    private fun loadRestaurant() {
        var n = SplashActivity.num_current_page
        Log.i("resp","Current_page: $n")
        Log.i("resp","${SplashActivity.coutryType}")
        val retrofit = Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val api = retrofit.create(Api::class.java)
        api.getRestaurants(SplashActivity.coutryType,SplashActivity.num_current_page).enqueue(object : Callback<RespData> {
            override fun onResponse(call: Call<RespData>, response: Response<RespData>) {
                if(response.isSuccessful &&  response.body() != null)
                {
                    Log.d("resp", response.body().toString())
                    restList!!.value = response.body()!!.restaurants
                    setData(response.body()!!.restaurants)

                }
            }
            override fun onFailure(call: Call<RespData>, t: Throwable) {}
        })
    }
    fun setData(restaList: List<RestaurantData>)
    {
        var l = SplashActivity.restaurantDataMemory.get("res")
        if (l == null)
        {
            SplashActivity.restaurantDataMemory.put("res", restaList)
        }
        else
        {
            l = l + restaList
            SplashActivity.restaurantDataMemory.put("res", l)
        }
        SplashActivity.num_current_page++
    }
}