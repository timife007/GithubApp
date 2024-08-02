package com.timife.githubapp.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.githubapp.domain.entities.userprofile.UserProfileData
import com.timife.githubapp.domain.usecases.ReposUseCase
import com.timife.githubapp.domain.usecases.UserProfileUseCase
import com.timife.githubapp.presentation.uistates.UserProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val userProfileUseCase: UserProfileUseCase,
    private val reposUseCase: ReposUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserProfileUiState>(UserProfileUiState.Loading)
    val uiState: StateFlow<UserProfileUiState> = _uiState

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    init {
        savedStateHandle.get<String>("username")?.let { user ->
            _title.update {
                user
            }
            fetchUserProfile(user)
        }
    }

    private fun fetchUserProfile(user: String) {
        viewModelScope.launch {
            userProfileUseCase(user)
                .combine(reposUseCase(user)) { profile, repos ->
                    UserProfileUiState.Success(
                        UserProfileData(
                            profile = profile,
                            repos = repos
                        )
                    )
                }.catch { e ->
                    _uiState.value = UserProfileUiState.Error(e.message ?: "Unknown Error")
                }.stateIn(
                    scope = this,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = UserProfileUiState.Loading,
                ).collect {
                    _uiState.value = it
                }
        }
    }
}
