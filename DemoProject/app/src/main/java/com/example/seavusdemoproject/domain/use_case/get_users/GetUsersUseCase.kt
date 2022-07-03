package com.example.seavusdemoproject.domain.use_case.get_users

import com.example.seavusdemoproject.domain.model.User
import com.example.seavusdemoproject.domain.repository.PhotoRepository
import com.example.seavusdemoproject.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val photoRepository: PhotoRepository
) {

    // This use case fetches the users and the associated photo.
    suspend operator fun invoke(): List<User>?{
        val users  = userRepository.fetchUsers().body()

        if (users != null) {
            for (user in users) {
                try {
                    val photo = photoRepository.fetchPhotoById(user.id).body()
                    user.photoUrl = photo?.first()?.thumbnailUrl ?: ""
                } catch (e: Exception) {
                    print("GetUsersUseCase Exception ${e.message}")
                }
            }
        }

        return users
    }

}