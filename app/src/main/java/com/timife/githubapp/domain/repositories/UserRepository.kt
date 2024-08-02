package com.timife.githubapp.domain.repositories

import com.timife.githubapp.domain.entities.repos.Repo
import com.timife.githubapp.domain.entities.userprofile.UserProfile
import com.timife.githubapp.domain.entities.users.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(query: String): Flow<List<User>>

    fun getUserProfile(user: String): Flow<UserProfile>

    fun getRepos(user: String): Flow<List<Repo>>
}