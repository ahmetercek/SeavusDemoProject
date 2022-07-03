package com.example.seavusdemoproject.domain.use_case.check_user_isfav

import com.example.seavusdemoproject.domain.repository.UserRepository
import javax.inject.Inject

class IsUserFavoriteUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(userId: Int) = repository.isUserFavorite(userId)

}