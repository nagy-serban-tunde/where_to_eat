package com.example.wheretoeat.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wheretoeat.Database.Entities.ProfileData

@Database(entities = arrayOf(ProfileData::class), version = 1, exportSchema = false)
public abstract class WhereToEatDatabaseDatabase : RoomDatabase() {

//    abstract fun foodItemsDao(): FoodItemsDao

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