package com.example.wheretoeat.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.wheretoeat.Database.Entities.FavouriteRestaurantData

@Dao
interface FavouriteRestaurnatDao {

    @Query("SELECT * FROM favouriterestaurant where :id = user_id ORDER BY id ASC")
    fun getFavouriteRestaurant(id:Long): LiveData<List<FavouriteRestaurantData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favoriteRestaurantData: FavouriteRestaurantData)

    @Delete
    suspend fun deleteProfile(favoriteRestaurantData: FavouriteRestaurantData)

    @Query("DELETE FROM favouriterestaurant")
    suspend fun deleteAll()

}