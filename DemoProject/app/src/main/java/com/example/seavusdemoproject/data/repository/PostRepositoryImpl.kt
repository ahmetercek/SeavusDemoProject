package com.example.seavusdemoproject.data.repository

import com.example.seavusdemoproject.data.remote.api.ApiService
import com.example.seavusdemoproject.domain.model.Post
import com.example.seavusdemoproject.domain.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PostRepository {

    override suspend fun fetchPostsByUserId(userId: Int): Response<List<Post>> = withContext(
        Dispatchers.IO) {
        val posts = apiService.fetchPostsByUserId(userId)
        posts
    }

}