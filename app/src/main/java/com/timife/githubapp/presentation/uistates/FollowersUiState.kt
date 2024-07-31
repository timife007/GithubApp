package com.timife.githubapp.presentation.uistates

import com.timife.githubapp.domain.model.users.User

sealed class FollowersUiState {
    data class Success(val users: List<User>): FollowersUiState()
    data class Error(val message: String): FollowersUiState()
    data object Loading: FollowersUiState()
}