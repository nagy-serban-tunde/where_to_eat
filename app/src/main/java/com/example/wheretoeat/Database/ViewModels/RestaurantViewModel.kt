package com.example.wheretoeat.Database.ViewModels

import android.util.Log
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
        val retrofit = Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val api = retrofit.create(Api::class.java)
        api.getRestaurants("US",SplashActivity.num_current_page).enqueue(object : Callback<RespData> {
            override fun onResponse(call: Call<RespData>, response: Response<RespData>) {
                if(response.isSuccessful)
                {
                    Log.d("resp", response.body().toString())
                    restList!!.value = response.body()!!.restaurants
                }
            }
            override fun onFailure(call: Call<RespData>, t: Throwable) {}
        })
        SplashActivity.num_current_page++
    }
}