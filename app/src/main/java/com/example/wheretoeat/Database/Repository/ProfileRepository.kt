package com.example.wheretoeat.Database.Repository

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.wheretoeat.Database.Dao.ProfileDao
import com.example.wheretoeat.Database.Entities.ProfileData

class ProfileRepository(private val profileDao : ProfileDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getProfile(): List<ProfileData> {
        return profileDao.getProfile()
    }

    @WorkerThread
    suspend fun insert(profile: ProfileData)
    {
        profileDao.insert(profile)
    }
    @WorkerThread
    suspend fun deleteProfile(profile: ProfileData) {
        profileDao.deleteProfile(profile)
    }

    @WorkerThread
    suspend fun deleteAll()
    {
        profileDao.deleteAll()
    }
}