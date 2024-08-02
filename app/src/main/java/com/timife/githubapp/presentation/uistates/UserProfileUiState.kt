package com.timife.githubapp.presentation.uistates

import com.timife.githubapp.domain.model.repos.Repo
import com.timife.githubapp.domain.model.userprofile.UserProfile

sealed class UserProfileUiState {
    data class Success(val data: UserProfileData) : UserProfileUiState()
    data class Error(val message: String) : UserProfileUiState()
    data object Loading : UserProfileUiState()
}

data class UserProfileData(
    val profile: UserProfile,
    val repos: List<Repo>
)