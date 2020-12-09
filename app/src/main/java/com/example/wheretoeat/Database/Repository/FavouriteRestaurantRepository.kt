package com.example.wheretoeat.Database.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.wheretoeat.Database.Dao.FavouriteRestaurnatDao
import com.example.wheretoeat.Database.Dao.ProfileDao
import com.example.wheretoeat.Database.Entities.FavouriteRestaurantData
import com.example.wheretoeat.Database.Entities.ProfileData

class FavouriteRestaurantRepository(private val favouriteRestaurnatDao: FavouriteRestaurnatDao) {

    val allfavouriterestaurant: LiveData<List<FavouriteRestaurantData>> = favouriteRestaurnatDao.getFavouriteRestaurant(1)
    @Suppress("RedundantSuspendModifier")

    fun getFavouriteRestaurant(id : Long)  : LiveData<List<FavouriteRestaurantData>>
    {
        return favouriteRestaurnatDao.getFavouriteRestaurant(id)
    }

    @WorkerThread
    suspend fun insert(favoriteRestaurantData: FavouriteRestaurantData)
    {
        favouriteRestaurnatDao.insert(favoriteRestaurantData)
    }
    @WorkerThread
    suspend fun deleteFood(favoriteRestaurantData: FavouriteRestaurantData) {
        favouriteRestaurnatDao.deleteProfile(favoriteRestaurantData)
    }

    @WorkerThread
    suspend fun deleteAll()
    {
        favouriteRestaurnatDao.deleteAll()
    }

}