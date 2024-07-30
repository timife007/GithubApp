package com.timife.githubapp.data.repositories

import com.timife.githubapp.data.datasources.remote.RemoteDatasource
import com.timife.githubapp.domain.model.users.User
import com.timife.githubapp.domain.repositories.FollowersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FollowersRepositoryImpl @Inject constructor(
    private val remoteDatasource: RemoteDatasource
) : FollowersRepository {
    override fun getFollowers(): Flow<List<User>> {
        TODO("Not yet implemented")
    }

    override fun getFollowing(): Flow<List<User>> {
        TODO("Not yet implemented")
    }

}