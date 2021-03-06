package com.example.seavusdemoproject.domain.repository

import com.example.seavusdemoproject.domain.model.User
import retrofit2.Response

interface UserRepository {

    suspend fun fetchUsers(): Response<List<User>>

    suspend fun addUserToFavorites(favUserId: Int)

    suspend fun isUserFavorite(user_id : Int):  Boolean

    suspend fun deleteUserFromFavorites(favUserId: Int)

}