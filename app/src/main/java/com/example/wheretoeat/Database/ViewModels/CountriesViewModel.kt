package com.example.wheretoeat.Database.ViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wheretoeat.Database.Entities.CountriesData
import com.example.wheretoeat.Database.Entities.RespDataCountries
import com.example.wheretoeat.Fragments.SplashFragment
import com.example.wheretoeat.Network.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountriesViewModel : ViewModel()
{
    private var countriesList: MutableLiveData<List<CountriesData>>? = null
    val getCountries: MutableLiveData<List<CountriesData>>
        get() {
            if (countriesList == null) {
                countriesList = MutableLiveData<List<CountriesData>>()
                loadCountries()
            }
            return countriesList as MutableLiveData<List<CountriesData>>
        }
    private fun loadCountries() {
        val retrofit = Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val api = retrofit.create(Api::class.java)
        api.getCountries().enqueue(object : Callback<RespDataCountries> {
            override fun onResponse(call: Call<RespDataCountries>, response: Response<RespDataCountries>) {
                if(response.isSuccessful &&  response.body() != null)
                {
                    Log.d("resp", response.body().toString())
                    setData(response.body()!!.countries)
                }
            }
            override fun onFailure(call: Call<RespDataCountries>, t: Throwable) {}
        })
    }
    fun setData(countries: ArrayList<String>)
    {
        SplashFragment.countriesDataMemory.put("countries", countries)

    }
}