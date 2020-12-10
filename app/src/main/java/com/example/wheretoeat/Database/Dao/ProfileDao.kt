package com.example.wheretoeat.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.wheretoeat.Database.Entities.ProfileData

@Dao
interface ProfileDao {

    @Query("SELECT * FROM profile ORDER BY id ASC")
    suspend fun getProfile(): List<ProfileData>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(profile: ProfileData)

    @Update
    suspend fun update(profile: ProfileData)

    @Delete
    suspend fun deleteProfile(profile: ProfileData)

    @Query("DELETE FROM profile")
    suspend fun deleteAll()

}