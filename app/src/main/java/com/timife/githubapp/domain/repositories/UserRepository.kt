package com.timife.githubapp.domain.repositories

import com.timife.githubapp.domain.Result
import com.timife.githubapp.domain.model.repos.Repo
import com.timife.githubapp.domain.model.userprofile.UserProfile
import com.timife.githubapp.domain.model.users.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(query:String): Flow<List<User>>

    fun getUserProfile(user:String): Flow<UserProfile>

    fun getRepos(user: String): Flow<Result<List<Repo>>>
}