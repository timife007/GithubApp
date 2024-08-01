package com.timife.githubapp.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.githubapp.domain.Result
import com.timife.githubapp.domain.usecases.UserProfileUseCase
import com.timife.githubapp.presentation.uistates.UserProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val userProfileUseCase: UserProfileUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _uiState = MutableStateFlow<UserProfileUiState>(UserProfileUiState.Loading)
    val uiState: StateFlow<UserProfileUiState> = _uiState

    init {
        savedStateHandle.get<String>("username")?.let { user ->
            getUsers(user)
        }
    }

    private fun getUsers(user:String){
        viewModelScope.launch {
            _uiState.value = UserProfileUiState.Loading
            userProfileUseCase(user).catch {
                _uiState.value = UserProfileUiState.Error(it.localizedMessage ?: "Unknown Error occurred")
            }.collect{profile ->
                _uiState.value = UserProfileUiState.Success(profile)
            }
        }
    }
}