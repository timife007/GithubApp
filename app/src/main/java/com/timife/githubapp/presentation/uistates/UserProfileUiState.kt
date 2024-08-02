package com.timife.githubapp.presentation.uistates

import com.timife.githubapp.domain.entities.userprofile.UserProfileData

sealed class UserProfileUiState {
    data class Success(val data: UserProfileData) : UserProfileUiState()
    data class Error(val message: String) : UserProfileUiState()
    data object Loading : UserProfileUiState()
}

