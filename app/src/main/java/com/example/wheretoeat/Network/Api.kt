package com.example.wheretoeat.Network

import com.example.wheretoeat.Database.Entities.RespData
import com.example.wheretoeat.Database.Entities.RespDataCountries
import org.json.JSONArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {
    @GET("stats")
    fun getStats(): Call<JSONArray>

    @GET("countries")
    fun getCountries(): Call<RespDataCountries>

    @GET("cities")
    fun getCities(): Call<JSONArray>

    @GET("restaurants")
    fun getRestaurants(@Query("country")state:String,@Query("page")page:Int): Call<RespData>

    companion object {
//        const val BASE_URL = "https://opentable.herokuapp.com/api/"
        const val BASE_URL = "https://ratpark-api.imok.space/"
    }
}