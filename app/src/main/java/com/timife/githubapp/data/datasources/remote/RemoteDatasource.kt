package com.timife.githubapp.data.datasources.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path


interface RemoteDatasource {

    @GET("/search/users")
    suspend fun getUsers(): ResponseBody

    @GET("/users/{username}")
    suspend fun getUserProfile(@Path("username") username:String): ResponseBody

    @GET("/users/{username}/followers")
    suspend fun getUserFollowers(@Path("username") username: String):ResponseBody

    @GET("/users/{username}/following")
    suspend fun getUserFollowing(@Path("username") username: String):ResponseBody

    @GET("/users/{username}/repos")
    suspend fun getUserRepos():ResponseBody

}