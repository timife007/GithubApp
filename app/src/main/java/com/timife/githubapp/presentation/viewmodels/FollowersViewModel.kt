package com.timife.githubapp.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.githubapp.domain.usecases.FollowersUseCase
import com.timife.githubapp.presentation.uistates.FollowersUiState
import com.timife.githubapp.presentation.uistates.toUserResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowersViewModel @Inject constructor(
    private val followersUseCase: FollowersUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow<FollowersUiState>(FollowersUiState.Loading)
    val uiState: StateFlow<FollowersUiState> = _uiState

    private val _title = MutableStateFlow<String>("")
    val title: StateFlow<String> = _title

    init {
        savedStateHandle.get<String>("username")?.let { user ->
            getFollowers(user)
            _title.update {
                user
            }
        }
    }


    private fun getFollowers(user: String) {
        _uiState.value = FollowersUiState.Loading
        viewModelScope.launch {
            followersUseCase(user).catch {
                _uiState.value = FollowersUiState.Error(it.message ?: "Unknown Error")
            }.collect {
                _uiState.value = FollowersUiState.Success(it.map { user -> user.toUserResult() })
            }
        }
    }


}