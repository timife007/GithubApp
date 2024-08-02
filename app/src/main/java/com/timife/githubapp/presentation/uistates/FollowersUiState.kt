package com.timife.githubapp.presentation.uistates

sealed class FollowersUiState {
    data class Success(val users: List<UserResult>) : FollowersUiState()
    data class Error(val message: String) : FollowersUiState()
    data object Loading : FollowersUiState()
}