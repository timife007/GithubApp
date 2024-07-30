package com.timife.githubapp.domain.model.users


import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

data class User(
    val avatarUrl: String,
    val followersUrl: String,
    val followingUrl: String,
    val id: Int,
    val username: String,
    val reposUrl: String,
    val url: String
)