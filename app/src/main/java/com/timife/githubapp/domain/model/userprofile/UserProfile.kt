package com.timife.githubapp.domain.model.userprofile


import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

data class UserProfile(
    val bio: String,
    val followers: Int,
    val followersUrl: String,
    val following: Int,
    val followingUrl: String,
    val id: Int,
    val location: String,
    val username: String,
    val name: String,
    val publicRepos: Int,
    val reposUrl: String,
    val url: String,
    val avatarUrl:String
)