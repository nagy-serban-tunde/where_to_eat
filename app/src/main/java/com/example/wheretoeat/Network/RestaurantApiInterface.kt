package com.example.wheretoeat.Network

import android.util.Log
import com.example.wheretoeat.Database.Entities.ResponseDataClass
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://opentable.herokuapp.com/api/"

interface RestaurantApiInterface {
    @GET("stats")
    fun getStats(): Call<JSONArray>

    @GET("countries")
    fun getCountries(): Call<JSONArray>

    @GET("cities")
    fun getCities(): Call<JSONArray>

    @GET("restaurants")
    fun getRestaurants(@Query("state")state:String): Call<ResponseDataClass>

    companion object {
        private val logger = HttpLoggingInterceptor()
        private val okHttp = OkHttpClient.Builder().addInterceptor(logger)

        val endpoints = createUrl(BASE_URL)

        fun createUrl(url:String) : RestaurantApiInterface {
            val retrofit = Retrofit.Builder().baseUrl(url)
                                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .client(okHttp.build())
                                    .build()
            return retrofit.create(RestaurantApiInterface::class.java)
        }
    }
}