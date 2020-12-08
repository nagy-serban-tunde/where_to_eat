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

    val allProfile: LiveData<List<ProfileData>>

    private val repository: ProfileRepository

    init{
        val profileDao = WhereToEatDatabaseDatabase.getDatabase(application).profileDao()
        repository = ProfileRepository(profileDao)
        allProfile = repository.allProfile
    }

    fun insert(profile: ProfileData) = viewModelScope.launch {
        repository.insert(profile)
    }

    fun deleteFood(profile: ProfileData)
    {
        viewModelScope.launch (Dispatchers.IO ){
            repository.deleteFood(profile)
        }
    }
    fun deleteAll()
    {
        viewModelScope.launch (Dispatchers.IO ){
            repository.deleteAll()
        }
    }
}