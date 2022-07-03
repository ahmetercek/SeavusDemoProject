package com.example.seavusdemoproject.data.repository

import com.example.seavusdemoproject.data.local.dao.UserDao
import com.example.seavusdemoproject.data.local.entity.UserEntity
import com.example.seavusdemoproject.data.remote.api.ApiService
import com.example.seavusdemoproject.domain.model.User
import com.example.seavusdemoproject.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDao : UserDao
) : UserRepository {

    /*
    * We consider a function main-safe when it doesn't block UI updates on the main thread.
    * Use the withContext() function from the coroutines library to move the execution of a coroutine to a different thread
    * withContext(Dispatchers.IO) moves the execution of the coroutine to an I/O thread,
    * making our calling function main-safe and enabling the UI to update as needed.
    * */
    override suspend fun fetchUsers(): Response<List<User>> = withContext(
        Dispatchers.IO) {
        val users = apiService.fetchUsers()
        users
    }

    override suspend fun addUserToFavorites(favUserId: Int) = withContext(
        Dispatchers.IO) {
        val userEntity = UserEntity(uid = favUserId, userId= favUserId)
        userDao.insertUser(userEntity)
    }

    override suspend fun isUserFavorite(user_id: Int): Boolean  = withContext(
        Dispatchers.IO) {
        userDao.findById(user_id)
    }

    override suspend fun deleteUserFromFavorites(favUserId: Int)   = withContext(
        Dispatchers.IO) {
        userDao.deleteById(favUserId)
    }

}