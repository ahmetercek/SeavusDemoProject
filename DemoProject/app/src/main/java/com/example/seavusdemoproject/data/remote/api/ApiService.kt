package com.example.seavusdemoproject.data.remote.api

import com.example.seavusdemoproject.domain.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/users")
    suspend fun fetchUsers() : Response<List<User>>

}