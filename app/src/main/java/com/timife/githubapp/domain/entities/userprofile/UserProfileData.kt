package com.timife.githubapp.domain.entities.userprofile

import com.timife.githubapp.domain.entities.repos.Repo
import com.timife.githubapp.domain.entities.userprofile.UserProfile

data class UserProfileData(
    val profile: UserProfile,
    val repos: List<Repo>
)