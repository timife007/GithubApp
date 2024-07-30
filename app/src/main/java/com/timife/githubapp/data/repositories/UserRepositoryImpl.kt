package com.timife.githubapp.data.repositories

import com.timife.githubapp.data.datasources.remote.RemoteDatasource
import com.timife.githubapp.domain.model.repos.Repo
import com.timife.githubapp.domain.model.userprofile.UserProfile
import com.timife.githubapp.domain.model.users.User
import com.timife.githubapp.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val remoteDatasource: RemoteDatasource
) : UserRepository {
    override fun getUsers(): Flow<List<User>> {
        TODO("Not yet implemented")
    }

    override fun getUserProfile(): Flow<UserProfile> {
        TODO("Not yet implemented")
    }

    override fun getRepos(): Flow<List<Repo> >{
        TODO("Not yet implemented")
    }
}