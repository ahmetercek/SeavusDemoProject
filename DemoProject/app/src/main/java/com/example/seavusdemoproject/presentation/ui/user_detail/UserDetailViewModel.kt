package com.example.seavusdemoproject.presentation.ui.user_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seavusdemoproject.domain.model.Post
import com.example.seavusdemoproject.domain.model.Todo
import com.example.seavusdemoproject.domain.use_case.get_user_posts.GetPostsByUserIdUseCase
import com.example.seavusdemoproject.domain.use_case.get_user_todos.GetTodosByUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getTodosByUserIdUseCase: GetTodosByUserIdUseCase,
    private val getPostsByUserIdUseCase: GetPostsByUserIdUseCase
): ViewModel() {

    val todos: MutableLiveData<List<Todo>> = MutableLiveData()
    val posts: MutableLiveData<List<Post>> = MutableLiveData()

    fun fetchTodosByUserId(userId: Int){
        viewModelScope.launch {
            todos.postValue(getTodosByUserIdUseCase(userId) ?: emptyList())
        }
    }

    fun fetchPostsByUserId(userId: Int){
        viewModelScope.launch {
            posts.postValue(getPostsByUserIdUseCase(userId) ?: emptyList())
        }
    }

}