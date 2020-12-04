package com.example.wheretoeat.Database.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wheretoeat.Database.Entities.RestaurantData
import com.example.wheretoeat.Network.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ViewModelRes : ViewModel() {

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
        val call: Call<List<RestaurantData>> = api.getRestaurants("IL")

        call.enqueue(object : Callback<List<RestaurantData>> {
            override fun onResponse(call: Call<List<RestaurantData>>, response: Response<List<RestaurantData>>) {
                Log.i("kiiras", response.body().toString())
                restList!!.setValue(response.body())
            }

            override fun onFailure(call: Call<List<RestaurantData>>, t: Throwable) {}
        })
    }
}