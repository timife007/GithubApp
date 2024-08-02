package com.timife.githubapp.domain.entities.users


data class User(
    val avatarUrl: String,
    val followersUrl: String,
    val followingUrl: String,
    val id: Int,
    val username: String,
    val reposUrl: String,
    val url: String
)