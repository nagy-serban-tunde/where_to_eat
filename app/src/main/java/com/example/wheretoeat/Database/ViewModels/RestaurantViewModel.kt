package com.example.wheretoeat.Database.ViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wheretoeat.Database.Entities.RespData
import com.example.wheretoeat.Database.Entities.RestaurantData
import com.example.wheretoeat.Fragments.SplashFragment
import com.example.wheretoeat.Network.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RestaurantViewModel : ViewModel() {

    var restList: MutableLiveData<List<RestaurantData>> = MutableLiveData()

    fun <RestaurantData> concatenate(vararg lists: List<RestaurantData>): List<RestaurantData> {
        return listOf(*lists).flatten()
    }
    fun loadRestaurant() {
        val retrofit = Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val api = retrofit.create(Api::class.java)
        api.getRestaurants(SplashFragment.countryType,SplashFragment.num_current_page).enqueue(object : Callback<RespData> {
            override fun onResponse(call: Call<RespData>, response: Response<RespData>) {
                if(response.isSuccessful &&  response.body() != null)
                {
                    restList.postValue(concatenate(restList.value ?: listOf(),response.body()!!.restaurants))
                    setData(response.body()!!.restaurants)
                    SplashFragment.num_current_page++
                }
            }
            override fun onFailure(call: Call<RespData>, t: Throwable) {}
        })
    }
    fun loadNewRestaurant() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(Api::class.java)
        api.getRestaurants(SplashFragment.countryType,SplashFragment.num_current_page).enqueue(object : Callback<RespData> {
            override fun onResponse(call: Call<RespData>, response: Response<RespData>) {
                if(response.isSuccessful &&  response.body() != null)
                {
                    restList.postValue(response.body()!!.restaurants)
                    setData(response.body()!!.restaurants)
                    SplashFragment.num_current_page++
                }
            }
            override fun onFailure(call: Call<RespData>, t: Throwable) {}
        })
    }
    fun setData(restaList: List<RestaurantData>)
    {
        var l = SplashFragment.restaurantDataMemory.get("res")
        if (l == null)
        {
            SplashFragment.restaurantDataMemory.put("res", restaList)
        }
        else
        {
            l = l + restaList
            SplashFragment.restaurantDataMemory.put("res", l)
        }
    }
}