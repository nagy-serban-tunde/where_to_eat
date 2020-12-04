package com.example.wheretoeat.Database.Entities

import androidx.lifecycle.MutableLiveData
import com.google.gson.annotations.SerializedName

class ResponseDataClass{
    @SerializedName("restaurants")
    lateinit var restaurants : MutableLiveData<List<RestaurantDataClass>>
}
