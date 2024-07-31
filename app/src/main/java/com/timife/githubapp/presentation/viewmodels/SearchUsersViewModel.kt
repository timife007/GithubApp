package com.timife.githubapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.githubapp.domain.Result
import com.timife.githubapp.domain.usecases.SearchUseCase
import com.timife.githubapp.domain.usecases.UserProfileUseCase
import com.timife.githubapp.presentation.uistates.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchUsersViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val userProfileUseCase: UserProfileUseCase,
) : ViewModel(){
    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Loading)
    val uiState: StateFlow<SearchUiState> = _uiState


    fun getUsers(user:String){
        _uiState.value = SearchUiState.Loading
        viewModelScope.launch {
            searchUseCase(user).collect{
                when(it){
                    is Result.Success -> {
                        _uiState.value = SearchUiState.Success(it.data)
                    }
                    is Result.Error -> {
                        _uiState.value = SearchUiState.Error(it.exception.message ?: "User not found")
                    }
                }
            }
        }
    }
}