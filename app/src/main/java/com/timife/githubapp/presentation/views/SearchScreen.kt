package com.timife.githubapp.presentation.views

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.timife.githubapp.domain.model.users.User
import com.timife.githubapp.presentation.uistates.SearchUiState
import com.timife.githubapp.presentation.viewmodels.SearchUsersViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    viewModel: SearchUsersViewModel = hiltViewModel(),
    modifier: Modifier
){
    val state by viewModel.uiState.collectAsState()
    val text = remember{mutableStateOf("")}
    val context = LocalContext.current

    Scaffold(modifier = modifier
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = text.value, onValueChange = {
                text.value = it
                viewModel.getUsers(it)
            })
            when(state){
                is SearchUiState.Loading -> {
                    LoadingView()
                    Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
                }
                is SearchUiState.Success -> {
                    val data = (state as SearchUiState.Success).users
                    SuccessView(data = data)
                    Toast.makeText(context, data.toString(), Toast.LENGTH_LONG).show()

                }
                is SearchUiState.Error -> {
                    val message = (state as SearchUiState.Error).message
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                }
            }
        }
//        Box(modifier = Modifier.fillMaxSize()) {
//
//
//        }
    }
}

@Composable
fun SuccessView(data:List<User>){
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(data) { user ->
            UserItem()
        }
    }
}

@Composable
fun LoadingView(){
    Box(modifier = Modifier){
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun ErrorView(error:String){
    Box(modifier = Modifier){
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