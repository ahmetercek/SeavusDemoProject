package com.example.seavusdemoproject.data.remote.api

import com.example.seavusdemoproject.domain.model.Photo
import com.example.seavusdemoproject.domain.model.Post
import com.example.seavusdemoproject.domain.model.Todo
import com.example.seavusdemoproject.domain.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/users")
    suspend fun fetchUsers() : Response<List<User>>

    @GET("/todos")
    suspend fun fetchTodosByUserId(@Query("userId") userId: Int) : Response<List<Todo>>

    @GET("/posts")
    suspend fun fetchPostsByUserId(@Query("userId") userId: Int) : Response<List<Post>>

    @GET("/photos")
    suspend fun fetchPhotoById(@Query("id") photoId: Int) : Response<List<Photo>>

}