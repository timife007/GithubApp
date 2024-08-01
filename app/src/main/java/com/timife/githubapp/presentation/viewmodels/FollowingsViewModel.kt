package com.timife.githubapp.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.githubapp.domain.Result
import com.timife.githubapp.domain.usecases.FollowersUseCase
import com.timife.githubapp.domain.usecases.FollowingUseCase
import com.timife.githubapp.presentation.uistates.FollowersUiState
import com.timife.githubapp.presentation.uistates.FollowingsUiState
import com.timife.githubapp.presentation.uistates.toUserResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FollowingsViewModel @Inject constructor(
    private val followingUseCase: FollowingUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _followsUiState = MutableStateFlow<FollowingsUiState>(FollowingsUiState.Loading)
    val followsUiState: StateFlow<FollowingsUiState> = _followsUiState

    init {
        savedStateHandle.get<String>("username")?.let { user ->
            getFollowings(user)
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