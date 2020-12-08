package com.example.wheretoeat.Database.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foods")
class ProfileData(

    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name = "name") val nameProfile: String,

    @ColumnInfo(name = "picture") val picture: String,

    @ColumnInfo(name = "address") val addressProfile: String,

    @ColumnInfo(name = "phone") val phoneProfle: String,

    @ColumnInfo(name = "email") val emailProfile : String

)