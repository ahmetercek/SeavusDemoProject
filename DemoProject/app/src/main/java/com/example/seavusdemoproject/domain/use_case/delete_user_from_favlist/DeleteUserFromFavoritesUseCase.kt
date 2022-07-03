package com.example.seavusdemoproject.domain.use_case.delete_user_from_favlist

import com.example.seavusdemoproject.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUserFromFavoritesUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(userId: Int) = repository.deleteUserFromFavorites(userId)

}