package com.example.seavusdemoproject.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.seavusdemoproject.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT EXISTS(SELECT * FROM fav_user WHERE user_id = :user_id)")
    fun findById(user_id: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(favUser : UserEntity)

    @Query("DELETE FROM fav_user WHERE user_id = :user_id")
    fun deleteById(user_id: Int)

}
