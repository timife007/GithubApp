package com.timife.githubapp.presentation.uistates

import com.timife.githubapp.domain.model.users.User

// Represents different states for the LatestNews screen
sealed class SearchUiState {
    data class Success(val users: List<UserResult>): SearchUiState()
    data class Error(val message: String): SearchUiState()
    data object Loading: SearchUiState()
}

