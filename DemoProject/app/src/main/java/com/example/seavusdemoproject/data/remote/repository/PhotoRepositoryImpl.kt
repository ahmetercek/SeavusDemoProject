package com.example.seavusdemoproject.data.remote.repository

import com.example.seavusdemoproject.data.remote.api.ApiService
import com.example.seavusdemoproject.domain.model.Photo
import com.example.seavusdemoproject.domain.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PhotoRepository {

    override suspend fun fetchPhotoById(photoId: Int): Response<List<Photo>> = withContext(
        Dispatchers.IO) {
        val photo = apiService.fetchPhotoById(photoId)
        photo
    }

}