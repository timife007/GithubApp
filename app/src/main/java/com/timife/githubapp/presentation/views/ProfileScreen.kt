package com.timife.githubapp.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.rounded.Inventory2
import androidx.compose.material3.Card
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.timife.githubapp.domain.model.userprofile.UserProfile
import com.timife.githubapp.navigation.Route
import com.timife.githubapp.presentation.uistates.SearchUiState
import com.timife.githubapp.presentation.uistates.UserProfileUiState
import com.timife.githubapp.presentation.viewmodels.UserProfileViewModel
import com.timife.githubapp.presentation.views.components.ErrorView
import com.timife.githubapp.presentation.views.components.LoadingView
import com.timife.githubapp.presentation.views.components.ProfileSuccessView

@Composable
fun ProfileScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: UserProfileViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()
    Scaffold(modifier = Modifier, topBar = {
        Row(
            modifier = Modifier
                .height(50.dp)
                .padding(10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null)
        }
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