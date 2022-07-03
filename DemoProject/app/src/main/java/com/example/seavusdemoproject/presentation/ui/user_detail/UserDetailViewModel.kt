package com.example.seavusdemoproject.presentation.ui.user_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seavusdemoproject.domain.model.Post
import com.example.seavusdemoproject.domain.model.Todo
import com.example.seavusdemoproject.domain.use_case.add_user_to_favlist.AddUserToFavoritesListUseCase
import com.example.seavusdemoproject.domain.use_case.check_user_isfav.IsUserFavoriteUseCase
import com.example.seavusdemoproject.domain.use_case.delete_user_from_favlist.DeleteUserFromFavoritesUseCase
import com.example.seavusdemoproject.domain.use_case.get_user_posts.GetPostsByUserIdUseCase
import com.example.seavusdemoproject.domain.use_case.get_user_todos.GetTodosByUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getTodosByUserIdUseCase: GetTodosByUserIdUseCase,
    private val getPostsByUserIdUseCase: GetPostsByUserIdUseCase,
    private val addUserToFavoritesListUseCase: AddUserToFavoritesListUseCase,
    private val deleteUserFromFavoritesUseCase: DeleteUserFromFavoritesUseCase,
    private val isUserFavoriteUseCase: IsUserFavoriteUseCase
): ViewModel() {

    val todos: MutableLiveData<List<Todo>> = MutableLiveData()
    val posts: MutableLiveData<List<Post>> = MutableLiveData()
    val isFavorite: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

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

    fun addUserToFavorites(userId: Int){
        viewModelScope.launch {
            addUserToFavoritesListUseCase(userId)
            checkUserFavorites(userId)
            delay(1000L)
            isLoading.postValue(false)
        }
    }

    fun deleteUserFromFavorites(userId: Int){
        viewModelScope.launch {
            deleteUserFromFavoritesUseCase(userId)
            checkUserFavorites(userId)
            delay(1000L)
            isLoading.postValue(false)
        }
    }

    fun checkUserFavorites(userId: Int){
        viewModelScope.launch {
            isFavorite.postValue(isUserFavoriteUseCase(userId)!!)
        }
    }

}