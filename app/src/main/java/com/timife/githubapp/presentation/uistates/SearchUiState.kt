package com.timife.githubapp.presentation.uistates

sealed class SearchUiState {
    data class Success(val users: List<UserResult>) : SearchUiState()
    data class Error(val message: String) : SearchUiState()
    data object Loading : SearchUiState()
}

