package com.timife.githubapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.githubapp.domain.Result
import com.timife.githubapp.domain.usecases.SearchUseCase
import com.timife.githubapp.domain.usecases.UserProfileUseCase
import com.timife.githubapp.presentation.uistates.SearchUiState
import com.timife.githubapp.presentation.uistates.UserResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchUsersViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val userProfileUseCase: UserProfileUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Error(""))
    val uiState: StateFlow<SearchUiState> = _uiState


    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    fun getUsers(user: String) {
        _uiState.value = SearchUiState.Loading
        viewModelScope.launch {

            searchUseCase(user).catch {
                _uiState.value = SearchUiState.Error(it.message ?: "Error fetching users")
            }.flatMapConcat { users ->

                val userProfileFlows = users.map {user ->
                    userProfileUseCase(user.username).map {
                        it.let {profile ->
                            UserResult(name = profile.name, username = profile.username, avatar = profile.avatarUrl, description = profile.bio)
                        }
                    }.catch {
                        _uiState.value = SearchUiState.Error(it.message ?: "Error fetching users")
                    }
                }
                combine(userProfileFlows){userResults ->
                    userResults
                }
            }.collect{
                _uiState.value = SearchUiState.Success(it.toList())
            }

        }
    }
}