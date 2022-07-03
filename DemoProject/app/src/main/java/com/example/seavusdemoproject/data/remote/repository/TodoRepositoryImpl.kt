package com.example.seavusdemoproject.data.remote.repository

import com.example.seavusdemoproject.data.remote.api.ApiService
import com.example.seavusdemoproject.domain.model.Todo
import com.example.seavusdemoproject.domain.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : TodoRepository {

    override suspend fun fetchTodosByUserId(userId: Int): Response<List<Todo>> = withContext(
        Dispatchers.IO) {
        val todos = apiService.fetchTodosByUserId(userId)
        todos
    }

}