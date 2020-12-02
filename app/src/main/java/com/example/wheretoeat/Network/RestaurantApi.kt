package com.example.wheretoeat.Network

//import com.google.gson.GsonBuilder
//import okhttp3.OkHttpClient
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import java.util.concurrent.TimeUnit
//
//const val BASEURL = "https://jsonplaceholder.typicode.com/"
//
//class RestaurantApi {
//    companion object{
//        private var retrofit: Retrofit?=null
//        fun getRestaurantApi(): Retrofit {
//            val gson = GsonBuilder()
//                .setLenient()
//                .create()
//            val okHttpClient = OkHttpClient.Builder()
//                .readTimeout(100, TimeUnit.SECONDS)
//                .connectTimeout(100, TimeUnit.SECONDS)
//                .build()
//            if (retrofit == null) {
//                retrofit = Retrofit.Builder()
//                    .baseUrl(BASEURL)
//                    .client(okHttpClient)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .build()
//            }
//            return retrofit!!
//        }
//    }
//}