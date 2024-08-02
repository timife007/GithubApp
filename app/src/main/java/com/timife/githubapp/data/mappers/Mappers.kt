package com.timife.githubapp.data.mappers

import com.timife.githubapp.data.model.repos.RepoDto
import com.timife.githubapp.data.model.userprofile.UserProfileDto
import com.timife.githubapp.data.model.users.UserDto
import com.timife.githubapp.domain.entities.repos.Repo
import com.timife.githubapp.domain.entities.userprofile.UserProfile
import com.timife.githubapp.domain.entities.users.User

fun UserProfileDto.toUserProfile():UserProfile{
    return UserProfile(
        bio = bio ?: "",
        followers = followers ?: 0,
        followersUrl = followersUrl ?: "",
        following = following ?: 0,
        followingUrl = followingUrl ?: "",
        id = id,
        location = location ?: "",
        username = login ?: "",
        name = name ?: "",
        publicRepos = publicRepos ?: 0,
        reposUrl = reposUrl ?: "",
        url = url ?: "",
        avatarUrl = avatarUrl ?: ""
    )
}

fun List<UserDto>.toListOfUsers(): List<User>{
    return this.map {
        User(
            avatarUrl = it.avatarUrl,
            followersUrl = it.followingUrl,
            followingUrl = it.followingUrl,
            id = it.id,
            username = it.login,
            reposUrl = it.reposUrl,
            url = it.url
        )
    }
}

fun List<RepoDto>.toListOfRepos():List<Repo>{
    return this.map {
        Repo(
            id = it.id,
            name = it.name,
            private = it.private,
            description = it.description ?: "",
            fork = it.fork,
            gitUrl = it.gitUrl,
            language = it.language ?: "",
            forksCount = it.forksCount,
            visibility = it.visibility,
            stars = it.stargazersCount
        )
    }
}

