package com.timife.githubapp.presentation.uistates

sealed class FollowingsUiState {
    data class Success(val users: List<UserResult>) : FollowingsUiState()
    data class Error(val message: String) : FollowingsUiState()
    data object Loading : FollowingsUiState()
}