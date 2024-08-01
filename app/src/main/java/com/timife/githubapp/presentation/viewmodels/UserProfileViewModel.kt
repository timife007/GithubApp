package com.timife.githubapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.githubapp.domain.Result
import com.timife.githubapp.domain.usecases.UserProfileUseCase
import com.timife.githubapp.presentation.uistates.UserProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val userProfileUseCase: UserProfileUseCase,
) : ViewModel(){
    private val _uiState = MutableStateFlow<UserProfileUiState>(UserProfileUiState.Loading)
    val uiState: StateFlow<UserProfileUiState> = _uiState


//    fun getUsers(user:String){
//        viewModelScope.launch {
//            userProfileUseCase(user).collect{
//                when(it){
//                    is Result.Success -> {
//                        _uiState.value = UserProfileUiState.Success(it.data)
//                    }
//                    is Result.Error -> {
//                        _uiState.value = UserProfileUiState.Error(it.exception.message ?: "User Profile not found")
//                    }
//                }
//            }
//        }
//    }
}