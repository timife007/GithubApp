package com.timife.githubapp.domain.repositories

import com.timife.githubapp.domain.model.repos.Repo
import com.timife.githubapp.domain.model.userprofile.UserProfile
import com.timife.githubapp.domain.model.users.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(): Flow<List<User>>

    fun getUserProfile(): Flow<UserProfile>

    fun getRepos(): Flow<List<Repo>>
}