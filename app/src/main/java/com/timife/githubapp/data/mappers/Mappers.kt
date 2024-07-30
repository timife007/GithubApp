package com.timife.githubapp.data.mappers

import com.timife.githubapp.data.model.userprofile.UserProfileDto
import com.timife.githubapp.data.model.users.UserDto
import com.timife.githubapp.domain.model.userprofile.UserProfile
import com.timife.githubapp.domain.model.users.User

fun UserProfileDto.toUserProfile():UserProfile{
    return UserProfile(
        bio = bio,
        followers = followers,
        followersUrl = followersUrl,
        following = following,
        followingUrl = followingUrl,
        id = id,
        location = location,
        username = login,
        name = name,
        publicRepos = publicRepos,
        reposUrl = reposUrl,
        url = url,
        avatarUrl = avatarUrl
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