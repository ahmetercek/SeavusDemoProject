package com.example.seavusdemoproject.domain.use_case.add_user_to_favlist


import com.example.seavusdemoproject.domain.repository.UserRepository
import javax.inject.Inject

class AddUserToFavoritesListUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(userId: Int) = repository.addUserToFavorites(userId)

}