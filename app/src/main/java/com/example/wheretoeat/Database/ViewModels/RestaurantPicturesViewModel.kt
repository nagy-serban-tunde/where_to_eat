package com.example.wheretoeat.Database.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.wheretoeat.Database.Entities.RestaurantPicturesData
import com.example.wheretoeat.Database.Repository.RestaurantPicturesRepository
import com.example.wheretoeat.Database.WhereToEatDatabaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantPicturesViewModel(application: Application) : AndroidViewModel(application) {

    var allRestaurantPictures: List<RestaurantPicturesData>? = null

    var oneRestaurantPictures: LiveData<List<RestaurantPicturesData>> ? = null

    private val repository: RestaurantPicturesRepository

    init{
        val restaurantpictureDao = WhereToEatDatabaseDatabase.getDatabase(application).restaurantsPicturesDao()
        repository = RestaurantPicturesRepository(restaurantpictureDao)
    }

    fun getRestaurantPictures()
    {
        viewModelScope.launch {
            allRestaurantPictures = repository.getRestaurantPictures()
        }
    }

    fun getRestaurantPicturesPhone(phone : String)
    {
        viewModelScope.launch {
            oneRestaurantPictures = repository.getRestaurantPicturesPhone(phone)
        }
    }

    fun insert(restaurantpicture: RestaurantPicturesData) = viewModelScope.launch {
        repository.insert(restaurantpicture)
    }

    fun deleteProfile(restaurantpicture: RestaurantPicturesData)
    {
        viewModelScope.launch (Dispatchers.IO ){
            repository.deleteProfile(restaurantpicture)
        }
    }
    fun deleteAll()
    {
        viewModelScope.launch (Dispatchers.IO ){
            repository.deleteAll()
        }
    }
}