package com.example.seavusdemoproject.presentation.ui.user_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seavusdemoproject.domain.model.User
import com.example.seavusdemoproject.domain.use_case.get_users.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
): ViewModel() {

    val users: MutableLiveData<List<User>> = MutableLiveData()

    fun fetchUsers(){

        viewModelScope.launch {
            users.postValue(getUsersUseCase() ?: emptyList())
        }

    }
}