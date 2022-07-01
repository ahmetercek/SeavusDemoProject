package com.example.seavusdemoproject.domain.use_case.get_users

import com.example.seavusdemoproject.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke() = repository.fetchUsers().body()

}