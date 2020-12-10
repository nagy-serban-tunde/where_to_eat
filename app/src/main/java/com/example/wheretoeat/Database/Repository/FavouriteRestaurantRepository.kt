package com.example.wheretoeat.Database.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.wheretoeat.Database.Dao.FavouriteRestaurnatDao
import com.example.wheretoeat.Database.Entities.FavouriteRestaurantData

class FavouriteRestaurantRepository(private val favouriteRestaurnatDao: FavouriteRestaurnatDao) {

    val allfavouriterestaurant: LiveData<List<FavouriteRestaurantData>> = favouriteRestaurnatDao.getFavouriteRestaurant(1)
    @Suppress("RedundantSuspendModifier")
    fun getFavouriteRestaurant(id : Long)  : LiveData<List<FavouriteRestaurantData>>
    {
        return favouriteRestaurnatDao.getFavouriteRestaurant(id)
    }

    @WorkerThread
    fun getIdFavouriteRestaurant(phone : String) : LiveData<Int>
    {
        return favouriteRestaurnatDao.getIdFavouriteRestaurant(phone)
    }

    @WorkerThread
    suspend fun insert(favoriteRestaurantData: FavouriteRestaurantData)
    {
        favouriteRestaurnatDao.insert(favoriteRestaurantData)
    }

    @WorkerThread
    suspend fun deleteFavouriteRestaurant(favoriteRestaurantData: FavouriteRestaurantData) {
        favouriteRestaurnatDao.deleteFavouriteRestaurant(favoriteRestaurantData)
    }

    @WorkerThread
    suspend fun deleteAll()
    {
        favouriteRestaurnatDao.deleteAll()
    }

}