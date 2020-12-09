package com.example.wheretoeat.Database.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.wheretoeat.Database.Entities.ProfileData
import com.example.wheretoeat.Database.Repository.ProfileRepository
import com.example.wheretoeat.Database.WhereToEatDatabaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    var allProfile: List<ProfileData>? = null

    private val repository: ProfileRepository
    var onLoadingFinished: () -> Unit = {}

    init{
        val profileDao = WhereToEatDatabaseDatabase.getDatabase(application).profileDao()
        repository = ProfileRepository(profileDao)
//        viewModelScope.launch {
//            onLoadingFinished()
//        }
    }

    fun getProfiles()
    {
        viewModelScope.launch {
            allProfile = repository.getProfile()
            onLoadingFinished()
        }
    }

    fun insert(profile: ProfileData) = viewModelScope.launch {
        repository.insert(profile)
        getProfiles()
    }


    fun deleteFood(profile: ProfileData)
    {
        viewModelScope.launch (Dispatchers.IO ){
//            repository.deleteFood(profile)
        }
    }
    fun deleteAll()
    {
        viewModelScope.launch (Dispatchers.IO ){
            repository.deleteAll()
        }
    }
}