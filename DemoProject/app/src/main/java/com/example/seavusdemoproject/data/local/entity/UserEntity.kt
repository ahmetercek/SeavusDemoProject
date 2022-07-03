package com.example.seavusdemoproject.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_user")
data class UserEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "user_id") val userId: Int?
)
