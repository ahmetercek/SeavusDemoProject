package com.example.seavusdemoproject.presentation.ui.user_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seavusdemoproject.domain.model.Todo
import com.example.seavusdemoproject.domain.use_case.get_user_todos.GetTodosByUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getTodosByUserIdUseCase: GetTodosByUserIdUseCase
): ViewModel() {

    val todos: MutableLiveData<List<Todo>> = MutableLiveData()

    fun fetchTodosByUserId(userId: Int){
        viewModelScope.launch {
            todos.postValue(getTodosByUserIdUseCase(userId) ?: emptyList())
        }
    }

}