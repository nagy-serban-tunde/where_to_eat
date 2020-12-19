package com.example.wheretoeat.Database.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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

    fun update(profile: ProfileData) = viewModelScope.launch {
        repository.update(profile)
    }

    fun deleteProfile(profile: ProfileData)
    {
        viewModelScope.launch (Dispatchers.IO ){
            repository.deleteProfile(profile)
        }
    }
    fun deleteAll()
    {
        viewModelScope.launch (Dispatchers.IO ){
            repository.deleteAll()
        }
    }
}