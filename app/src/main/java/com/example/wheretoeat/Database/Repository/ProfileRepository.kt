package com.example.wheretoeat.Database.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.wheretoeat.Database.Dao.ProfileDao
import com.example.wheretoeat.Database.Entities.ProfileData

class ProfileRepository(private val profileDao : ProfileDao) {

    val allProfile: LiveData<List<ProfileData>> = profileDao.getProfile()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(profile: ProfileData)
    {
        profileDao.insert(profile)
    }

    suspend fun deleteFood(profile: ProfileData) {
        profileDao.deleteProfile(profile)
    }

    suspend fun deleteAll()
    {
        profileDao.deleteAll()
    }
}