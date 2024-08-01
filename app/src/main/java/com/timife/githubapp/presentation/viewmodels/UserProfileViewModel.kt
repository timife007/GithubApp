package com.timife.githubapp.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.githubapp.domain.Result
import com.timife.githubapp.domain.usecases.ReposUseCase
import com.timife.githubapp.domain.usecases.UserProfileUseCase
import com.timife.githubapp.presentation.uistates.UserProfileData
import com.timife.githubapp.presentation.uistates.UserProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val userProfileUseCase: UserProfileUseCase,
    private val reposUseCase: ReposUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _uiState = MutableStateFlow<UserProfileUiState>(UserProfileUiState.Loading)
    val uiState: StateFlow<UserProfileUiState> = _uiState


    private val _username = MutableStateFlow<String>("")
    val username: StateFlow<String> = _username

    init {
        savedStateHandle.get<String>("username")?.let { user ->
            _username.value = user
        }
    }

    val profile = userProfileUseCase(username.value)
        .combine(reposUseCase(username.value)){profile, repos ->
            UserProfileUiState.Success(UserProfileData(
                profile = profile,
                repos = repos
            ))
        }.catch {
            UserProfileUiState.Error(it.message ?: "Unknown Error")
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UserProfileUiState.Loading,
        )

//    private fun getUserProfile(user:String){
//        viewModelScope.launch {
//            _uiState.value = UserProfileUiState.Loading
//            userProfileUseCase(user).catch {
//                _uiState.value = UserProfileUiState.Error(it.localizedMessage ?: "Unknown Error occurred")
//            }.collect{profile ->
//                _uiState.value = UserProfileUiState.Success(profile)
//            }
//        }
//    }

    private fun getUserRepos(user:String){

    }
}