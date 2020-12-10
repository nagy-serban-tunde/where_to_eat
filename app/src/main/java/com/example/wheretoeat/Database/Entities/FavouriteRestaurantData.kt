package com.example.wheretoeat.Database.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "favouriterestaurant")

class FavouriteRestaurantData(

        @PrimaryKey(autoGenerate = true) val id: Int,

        @ColumnInfo(name = "user_id") val user_id: Int,

        @ColumnInfo(name = "picture") val restaurantpicture: String,

        @ColumnInfo(name = "restaurantname") val restaurantname: String,

        @ColumnInfo(name = "restaurantphone") val restaurantphone: String,

        @ColumnInfo(name = "restaurantcity") val restaurantcity: String,

        @ColumnInfo(name = "restaurantarea") val restaurantarea : String

)