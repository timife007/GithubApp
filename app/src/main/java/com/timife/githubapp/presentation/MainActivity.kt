package com.timife.githubapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.timife.githubapp.navigation.Route
import com.timife.githubapp.presentation.ui.theme.GithubAppTheme
import com.timife.githubapp.presentation.views.FollowersScreen
import com.timife.githubapp.presentation.views.FollowsScreen
import com.timife.githubapp.presentation.views.ProfileScreen
import com.timife.githubapp.presentation.views.SearchScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            GithubAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = Route.SearchScreen.route
                    ) {
                        composable(route = Route.SearchScreen.route) {
                            SearchScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController
                            )
                        }
                        composable(
                            route = Route.ProfileScreen.route + "/{username}",
                            arguments = listOf(
                                navArgument(
                                    name = "username"
                                ) {
                                    type = NavType.StringType
                                    defaultValue = ""
                                })
                        ) {
                            ProfileScreen(
                                modifier = Modifier,
                                navController = navController,
                                onNavigateBack = {
                                    navController.navigateUp()
                                })
                        }

                        composable(
                            route = Route.FollowersScreen.route + "/{username}",
                            arguments = listOf(
                                navArgument(
                                    name = "username"
                                ) {
                                    type = NavType.StringType
                                    defaultValue = ""
                                })
                        ) {
                            FollowersScreen(
                                modifier = Modifier,
                                navController = navController,
                                onNavigateBack = { navController.navigateUp() })
                        }

                        composable(
                            route = Route.FollowsScreen.route + "/{username}",
                            arguments = listOf(
                                navArgument(
                                    name = "username"
                                ) {
                                    type = NavType.StringType
                                    defaultValue = ""
                                })
                        ) {
                            FollowsScreen(
                                modifier = Modifier,
                                navController = navController,
                                onNavigateBack = {
                                    navController.navigateUp()
                                })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GithubAppTheme {
        Greeting("Android")
    }
}