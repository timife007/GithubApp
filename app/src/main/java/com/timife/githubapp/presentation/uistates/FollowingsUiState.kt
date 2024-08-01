package com.timife.githubapp.presentation.uistates

import com.timife.githubapp.domain.model.users.User

sealed class FollowingsUiState {
    data class Success(val users: List<UserResult>): FollowingsUiState()
    data class Error(val message: String): FollowingsUiState()
    data object Loading: FollowingsUiState()
}