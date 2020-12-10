package com.example.wheretoeat.Database.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.wheretoeat.Database.Entities.FavouriteRestaurantData
import com.example.wheretoeat.Database.Repository.FavouriteRestaurantRepository
import com.example.wheretoeat.Database.WhereToEatDatabaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteRestaurantViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var allFavouriteRestaurant: LiveData<List<FavouriteRestaurantData>>

    private val repository: FavouriteRestaurantRepository

    lateinit var id : LiveData<Int>

    init{
        val favouriteRestaurantDao = WhereToEatDatabaseDatabase.getDatabase(application).favouriteRestaurantDao()
        repository = FavouriteRestaurantRepository(favouriteRestaurantDao)
        allFavouriteRestaurant = repository.allfavouriterestaurant
    }

    fun getFavouriteRestaurants(id : Long)
    {
        allFavouriteRestaurant = repository.getFavouriteRestaurant(id)
    }

    fun getIdFavouriteRestaurant(phone : String)  {
        viewModelScope.launch {
            id = repository.getIdFavouriteRestaurant(phone)
        }
    }

    fun insert(favoriteRestaurantData: FavouriteRestaurantData) = viewModelScope.launch {
        repository.insert(favoriteRestaurantData)
    }

    fun deleteFavouriteRestaurant(favoriteRestaurantData: FavouriteRestaurantData)
    {
        viewModelScope.launch (Dispatchers.IO ){
            repository.deleteFavouriteRestaurant(favoriteRestaurantData)
        }
    }

    fun deleteAll()
    {
        viewModelScope.launch (Dispatchers.IO ){
            repository.deleteAll()
        }
    }
}