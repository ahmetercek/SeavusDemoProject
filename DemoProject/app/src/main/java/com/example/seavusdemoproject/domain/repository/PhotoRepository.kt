package com.example.seavusdemoproject.domain.repository

import com.example.seavusdemoproject.domain.model.Photo
import retrofit2.Response

interface PhotoRepository {

    suspend fun fetchPhotoById(photoId: Int): Response<List<Photo>>

}