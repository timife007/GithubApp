package com.timife.githubapp.presentation.uistates

import com.timife.githubapp.domain.model.users.User

data class UserResult(
    val name: String?,
    val username: String,
    val avatar: String,
    val description: String?
)

fun User.toUserResult(): UserResult {
    return UserResult(
        name = null,
        username = username,
        avatar = avatarUrl,
        description = null
    )
}