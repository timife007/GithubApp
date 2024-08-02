package com.timife.githubapp.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.timife.githubapp.navigation.Route
import com.timife.githubapp.presentation.uistates.UserProfileUiState
import com.timife.githubapp.presentation.viewmodels.UserProfileViewModel
import com.timife.githubapp.presentation.views.components.CustomAppBar
import com.timife.githubapp.presentation.views.components.ErrorView
import com.timife.githubapp.presentation.views.components.LoadingView
import com.timife.githubapp.presentation.views.components.ProfileSuccessView

@Composable
fun ProfileScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: UserProfileViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {

    val state by viewModel.uiState.collectAsState()
    val title = viewModel.title.collectAsState()

    Scaffold(modifier = Modifier, topBar = {
        CustomAppBar(
            modifier = modifier,
            title = title.value,
            subtitle = "Profile",
            onNavigateBack = onNavigateBack
        )
    }) { innerPaddings ->
        Surface(modifier = Modifier.padding(innerPaddings)) {
            when (state) {
                is UserProfileUiState.Success -> {
                    val data = (state as UserProfileUiState.Success).data
                    ProfileSuccessView(data = data, onNavigateToFollows = { username ->
                        navController.navigate(Route.FollowsScreen.route + "/${username}")
                    }, onNavigateToFollowers = { username ->
                        navController.navigate(Route.FollowersScreen.route + "/${username}")
                    })
                }

                is UserProfileUiState.Loading -> {
                    LoadingView()
                }

                is UserProfileUiState.Error -> {
                    val error = (state as UserProfileUiState.Error).message
                    ErrorView(error = error)
                }
            }
        }
    }

}


@Composable
fun TextWithLeadingIcon(leadingIcon: ImageVector?, text: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = Color.DarkGray,
                modifier = Modifier.size(12.dp)
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray
            )
        )
    }
}