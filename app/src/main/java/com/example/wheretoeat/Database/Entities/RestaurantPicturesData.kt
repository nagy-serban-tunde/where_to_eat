package com.example.wheretoeat.Database.Entities

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurantpictures")
class RestaurantPicturesData(
        @PrimaryKey(autoGenerate = true) val id: Int,

        @ColumnInfo(name = "restaurantphone") val restaurantphone: String,

        @ColumnInfo(name = "new_picture_string") val new_picture_string: String,

)