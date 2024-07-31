package com.timife.githubapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.githubapp.domain.Result
import com.timife.githubapp.domain.usecases.FollowersUseCase
import com.timife.githubapp.domain.usecases.FollowingUseCase
import com.timife.githubapp.presentation.uistates.FollowersUiState
import com.timife.githubapp.presentation.uistates.FollowingsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowingsViewModel @Inject constructor(
    private val followingUseCase: FollowingUseCase,
): ViewModel() {
    private val _uiState = MutableStateFlow<FollowingsUiState>(FollowingsUiState.Loading)
    val uiState: StateFlow<FollowingsUiState> = _uiState

    fun getFollowings(user: String) {
        viewModelScope.launch {
            followingUseCase(user).collect {
                when (it) {
                    is Result.Success -> {
                        _uiState.value = FollowingsUiState.Success(it.data)
                    }

                    is Result.Error -> {
                        _uiState.value = FollowingsUiState.Error(
                            it.exception.message ?: "Error fetching Followings"
                        )
                    }
                }
            }
        }
    }
}