package com.example.seavusdemoproject.domain.repository

import com.example.seavusdemoproject.domain.model.Todo
import retrofit2.Response

interface TodoRepository {

    suspend fun fetchTodosByUserId(userId: Int): Response<List<Todo>>

}