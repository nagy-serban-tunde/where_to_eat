package com.example.wheretoeat.Database.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.wheretoeat.Database.Entities.ResponseDataClass
import com.example.wheretoeat.Database.Entities.RestaurantDataClass
import com.example.wheretoeat.Network.RestaurantApiInterface
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RestaurantViewModel(application: Application) : AndroidViewModel(application) {
    var allRestaurant: MutableLiveData<List<RestaurantDataClass>>? = null

    fun getRestaurants(): MutableLiveData<List<RestaurantDataClass>>? {
        if ( allRestaurant == null)
        {
            allRestaurant = MutableLiveData<List<RestaurantDataClass>>()
            loadRestaurant()
        }
        return allRestaurant
    }
    private fun loadRestaurant() {
        RestaurantApiInterface.endpoints.getRestaurants("IL").enqueue(object : Callback<ResponseDataClass> {
            override fun onFailure(call: Call<ResponseDataClass>, t: Throwable) {
            }
            override fun onResponse(call: Call<ResponseDataClass>, response: Response<ResponseDataClass>) {
                 if (response.isSuccessful) {
                     allRestaurant = response.body()!!.restaurants
                 }
            }

        })
    }
}