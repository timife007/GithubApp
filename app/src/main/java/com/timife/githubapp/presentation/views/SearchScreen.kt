package com.timife.githubapp.presentation.views

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.timife.githubapp.domain.model.users.User
import com.timife.githubapp.presentation.Greeting
import com.timife.githubapp.presentation.ui.theme.GithubAppTheme
import com.timife.githubapp.presentation.uistates.SearchUiState
import com.timife.githubapp.presentation.uistates.UserResult
import com.timife.githubapp.presentation.viewmodels.SearchUsersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    modifier: Modifier
) {
    val viewModel = viewModel<SearchUsersViewModel>()
    val state by viewModel.uiState.collectAsState()
    var text by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(10.dp)) {

            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Search, contentDescription = null )
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
                    SuccessView(data = data)
                }

                is SearchUiState.Error -> {
                    val message = (state as SearchUiState.Error).message
                    ErrorView(error = message)
                }
            }
        }
    }
}

@Composable
fun SuccessView(data: List<UserResult>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(data) { user ->
            UserItem(user)
        }
    }
}

@Composable
fun LoadingView() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = Color.Black)
    }
}

@Composable
fun ErrorView(error: String) {
    Box(modifier = Modifier) {
        Text(
            text = error,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .align(
                    Alignment.Center
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    GithubAppTheme {
        SearchScreen(Modifier.padding(10.dp))
    }
}