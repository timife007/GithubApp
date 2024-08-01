package com.timife.githubapp.presentation.views.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.timife.githubapp.navigation.Route
import com.timife.githubapp.presentation.uistates.UserResult
import com.timife.githubapp.presentation.views.UserItem

@Composable
fun SuccessView(data: List<UserResult>, modifier: Modifier, navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(data) { user ->
            UserItem(modifier = Modifier.clickable {
                navController.navigate(Route.ProfileScreen.route + "/${user.username}")
            }, user)
        }
    }
}