package com.timife.githubapp.presentation.views

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.timife.githubapp.presentation.uistates.FollowingsUiState
import com.timife.githubapp.presentation.viewmodels.FollowingsViewModel
import com.timife.githubapp.presentation.views.components.CustomAppBar
import com.timife.githubapp.presentation.views.components.ErrorView
import com.timife.githubapp.presentation.views.components.LoadingView
import com.timife.githubapp.presentation.views.components.SuccessView

@Composable
fun FollowsScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: FollowingsViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val state by viewModel.followsUiState.collectAsState()
    val title = viewModel.title.collectAsState().value

    Scaffold(modifier = Modifier, topBar = {
        CustomAppBar(
            modifier = modifier,
            title = title,
            "Following",
            onNavigateBack = onNavigateBack
        )
    }) { innerPaddings ->

        Surface(modifier = modifier.padding(innerPaddings)) {
            Spacer(modifier = Modifier.height(10.dp))
            when (state) {
                is FollowingsUiState.Loading -> {
                    LoadingView()
                }

                is FollowingsUiState.Success -> {
                    val data = (state as FollowingsUiState.Success).users
                    SuccessView(data = data, modifier = Modifier, navController)
                }

                is FollowingsUiState.Error -> {
                    val message = (state as FollowingsUiState.Error).message
                    ErrorView(error = message)
                }
            }
        }

    }
}