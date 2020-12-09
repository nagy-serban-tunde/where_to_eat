package com.example.wheretoeat.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wheretoeat.Database.Dao.FavouriteRestaurnatDao
import com.example.wheretoeat.Database.Dao.ProfileDao
import com.example.wheretoeat.Database.Entities.FavouriteRestaurantData
import com.example.wheretoeat.Database.Entities.ProfileData

@Database(entities = [ProfileData::class, FavouriteRestaurantData::class], version = 1, exportSchema = false)
abstract class WhereToEatDatabaseDatabase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao
    abstract fun favouriteRestaurantDao(): FavouriteRestaurnatDao

    companion object {
        @Volatile
        private var INSTANCE: WhereToEatDatabaseDatabase? = null

        fun getDatabase(context: Context): WhereToEatDatabaseDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WhereToEatDatabaseDatabase::class.java,
                    "WhereToEat"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}