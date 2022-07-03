package com.example.seavusdemoproject.domain.repository

import com.example.seavusdemoproject.domain.model.Post
import retrofit2.Response

interface PostRepository {

    suspend fun fetchPostsByUserId(userId: Int): Response<List<Post>>

}