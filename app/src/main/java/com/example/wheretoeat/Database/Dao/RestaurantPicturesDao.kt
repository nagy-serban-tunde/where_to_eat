package com.example.wheretoeat.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.wheretoeat.Database.Entities.RestaurantPicturesData

@Dao
interface RestaurantPicturesDao {

    @Query("SELECT * FROM restaurantpictures ORDER BY id ASC")
    suspend fun getRestaurantPictures(): List<RestaurantPicturesData>

    @Query("SELECT * FROM restaurantpictures  where :phone = restaurantphone ORDER BY id ASC")
    fun getRestaurantPicturesPhone(phone : String): LiveData<List<RestaurantPicturesData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(restaurantpicture: RestaurantPicturesData)

    @Delete
    suspend fun deleteProfile(restaurantpicture: RestaurantPicturesData)

    @Query("DELETE FROM restaurantpictures")
    suspend fun deleteAll()
}