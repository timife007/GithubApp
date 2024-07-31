package com.timife.githubapp.presentation.uistates

import com.timife.githubapp.domain.model.userprofile.UserProfile

sealed class UserProfileUiState {
    data class Success(val users: UserProfile): UserProfileUiState()
    data class Error(val message: String): UserProfileUiState()
    data object Loading: UserProfileUiState()
}