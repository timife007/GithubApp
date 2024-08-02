package com.timife.githubapp.navigation

sealed class Route(val route:String){
    data object SearchScreen: Route("search_screen")
    data object ProfileScreen: Route("profile_screen")
    data object FollowersScreen: Route("followers_screen")

    data object FollowsScreen: Route("follows_screen")

}