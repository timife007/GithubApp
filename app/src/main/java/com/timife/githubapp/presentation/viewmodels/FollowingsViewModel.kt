package com.timife.githubapp.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.githubapp.domain.usecases.FollowingUseCase
import com.timife.githubapp.presentation.uistates.FollowingsUiState
import com.timife.githubapp.presentation.uistates.toUserResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowingsViewModel @Inject constructor(
    private val followingUseCase: FollowingUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _followsUiState = MutableStateFlow<FollowingsUiState>(FollowingsUiState.Loading)
    val followsUiState: StateFlow<FollowingsUiState> = _followsUiState

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    init {
        savedStateHandle.get<String>("username")?.let { user ->
            getFollowings(user)
            _title.update {
                user
            }
        }
    }


    private fun getFollowings(user: String) {
        viewModelScope.launch {
            followingUseCase(user).catch {
                _followsUiState.value = FollowingsUiState.Error(it.message ?: "Unknown Error")
            }.collect {
                _followsUiState.value =
                    FollowingsUiState.Success(it.map { user -> user.toUserResult() })
            }
        }
    }
}