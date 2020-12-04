package com.example.wheretoeat.Network

import com.example.wheretoeat.Database.Entities.ResponseDataClass
import com.example.wheretoeat.Database.Entities.RestaurantData
import org.json.JSONArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {
    @GET("stats")
    fun getStats(): Call<JSONArray>

    @GET("countries")
    fun getCountries(): Call<JSONArray>

    @GET("cities")
    fun getCities(): Call<JSONArray>

    @GET("restaurants")
    fun getRestaurants(@Query("state")state:String): Call<List<RestaurantData>>

    companion object {
        const val BASE_URL = "https://opentable.herokuapp.com/api/"
    }
}