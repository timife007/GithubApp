package com.timife.githubapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.githubapp.domain.Result
import com.timife.githubapp.domain.usecases.FollowersUseCase
import com.timife.githubapp.domain.usecases.FollowingUseCase
import com.timife.githubapp.presentation.uistates.FollowersUiState
import com.timife.githubapp.presentation.uistates.UserProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowersViewModel @Inject constructor(
    private val followersUseCase: FollowersUseCase,
    private val followingUseCase: FollowingUseCase,
):ViewModel() {
    private val _uiState = MutableStateFlow<FollowersUiState>(FollowersUiState.Loading)
    val uiState: StateFlow<FollowersUiState> = _uiState

    fun getFollowers(user:String){
        viewModelScope.launch {
            followersUseCase(user).collect{
                when(it){
                    is Result.Success -> {
                        _uiState.value = FollowersUiState.Success(it.data)
                    }
                    is Result.Error -> {
                        _uiState.value = FollowersUiState.Error(it.exception.message ?: "Error fetching Followers")
                    }
                }
            }
        }
    }

    fun getFollowings(user:String){
        viewModelScope.launch {
            followingUseCase(user).collect{
                when(it){
                    is Result.Success -> {

                    }
                    is Result.Error -> {

                    }
                }
            }
        }
    }

}