package com.timife.githubapp.data.datasources.remote

import com.timife.githubapp.data.model.repos.RepoDto
import com.timife.githubapp.data.model.userprofile.UserProfileDto
import com.timife.githubapp.data.model.users.SearchResponse
import com.timife.githubapp.data.model.users.UserDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RemoteDatasource {

    @GET("/search/users?page=2")
    suspend fun searchUsers(@Query("q") q: String): Response<SearchResponse>

    @GET("/users/{username}")
    suspend fun getUserProfile(@Path("username") username:String): Response<UserProfileDto>

    @GET("/users/{username}/followers")
    suspend fun getUserFollowers(@Path("username") username: String):Response<List<UserDto>>

    @GET("/users/{username}/following")
    suspend fun getUserFollowing(@Path("username") username: String):Response<List<UserDto>>

    @GET("/users/{username}/repos")
    suspend fun getUserRepos(@Path("username") username: String):Response<List<RepoDto>>

}