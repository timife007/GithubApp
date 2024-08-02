package com.timife.githubapp.presentation.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.timife.githubapp.presentation.ui.theme.GithubAppTheme
import com.timife.githubapp.presentation.uistates.SearchUiState
import com.timife.githubapp.presentation.viewmodels.SearchUsersViewModel
import com.timife.githubapp.presentation.views.components.CustomAppBar
import com.timife.githubapp.presentation.views.components.ErrorView
import com.timife.githubapp.presentation.views.components.LoadingView
import com.timife.githubapp.presentation.views.components.SuccessView

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: SearchUsersViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    var text by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current



    Scaffold(
        modifier = Modifier,
        topBar = {
            CustomAppBar(
                modifier = Modifier,
                title = "Github Search",
                subtitle = "",
                onNavigateBack = { /*TODO*/ },
                isHome = true
            )
        }
    ) { innerPaddings ->
        Surface(modifier = modifier.padding(innerPaddings)) {
            Column(
                modifier = Modifier.padding(
                    start = 10.dp,
                    end = 10.dp,
                    bottom = 10.dp,
                    top = 5.dp
                )
            ) {
                TextField(
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {
                        viewModel.getUsers(text)
                        focusManager.clearFocus(true)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text(
                        text = "Search Users",
                        modifier = Modifier,
                        style = TextStyle(color = Color.White)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                when (state) {
                    is SearchUiState.Loading -> {
                        LoadingView()
                    }

                    is SearchUiState.Success -> {
                        val data = (state as SearchUiState.Success).users
                        SuccessView(
                            data = data,
                            modifier = Modifier,
                            navController
                        )
                    }

                    is SearchUiState.Error -> {
                        val message = (state as SearchUiState.Error).message
                        ErrorView(error = message)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    GithubAppTheme {
        SearchScreen(Modifier.padding(10.dp), rememberNavController())
    }
}