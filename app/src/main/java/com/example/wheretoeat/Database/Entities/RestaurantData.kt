package com.example.wheretoeat.Database.Entities

data class RespData(
        val total_entries: Int,
        val per_page: Int,
        val current_page: Int,
        val restaurants: List<RestaurantData>
)

data class RestaurantData(val name: String,val imageurl: String, val lat: String, val lng: String, val city: String , val phonenumber: String)