package com.example.wheretoeat.Database.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.wheretoeat.Database.Dao.RestaurantPicturesDao
import com.example.wheretoeat.Database.Entities.FavouriteRestaurantData
import com.example.wheretoeat.Database.Entities.RestaurantPicturesData

class RestaurantPicturesRepository(private val  restaurantpicturesDao : RestaurantPicturesDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getRestaurantPictures(): List<RestaurantPicturesData> {
        return restaurantpicturesDao.getRestaurantPictures()
    }

    @WorkerThread
    suspend fun getRestaurantPicturesPhone(phone : String): LiveData<List<RestaurantPicturesData>> {
        return restaurantpicturesDao.getRestaurantPicturesPhone(phone)
    }

    @WorkerThread
    suspend fun insert(restaurantpicture: RestaurantPicturesData)
    {
        restaurantpicturesDao.insert(restaurantpicture)
    }

    @WorkerThread
    suspend fun deleteProfile(restaurantpicture: RestaurantPicturesData) {
        restaurantpicturesDao.deleteProfile(restaurantpicture)
    }

    @WorkerThread
    suspend fun deleteAll()
    {
        restaurantpicturesDao.deleteAll()
    }
}