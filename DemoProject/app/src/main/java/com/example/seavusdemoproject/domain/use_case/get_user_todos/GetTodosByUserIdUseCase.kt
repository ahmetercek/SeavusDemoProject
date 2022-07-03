package com.example.seavusdemoproject.domain.use_case.get_user_todos

import com.example.seavusdemoproject.domain.repository.TodoRepository
import javax.inject.Inject

class GetTodosByUserIdUseCase @Inject constructor(
    private val repository: TodoRepository
) {

    suspend operator fun invoke(userId: Int) = repository.fetchTodosByUserId(userId).body()

}