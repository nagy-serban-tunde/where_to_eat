package com.example.wheretoeat.Database.Entities

import com.google.gson.annotations.SerializedName

class RestaurantDataClass
{
    @SerializedName("name")
    lateinit var name : String

    @SerializedName("image_url")
    lateinit var image_url : String

    @SerializedName("lat")
    lateinit var latitude : String

    @SerializedName("lng")
    lateinit var longitude : String

    @SerializedName("city")
    lateinit var city : String

    @SerializedName("phone")
    lateinit var phonenumber : String

}