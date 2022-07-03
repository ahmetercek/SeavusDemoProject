package com.example.seavusdemoproject.domain.use_case.get_user_posts

import com.example.seavusdemoproject.domain.repository.PostRepository
import javax.inject.Inject

class GetPostsByUserIdUseCase @Inject constructor(
    private val repository: PostRepository
) {

    suspend operator fun invoke(userId: Int) = repository.fetchPostsByUserId(userId).body()

}