package com.timife.githubapp.data.repositories

import com.timife.githubapp.data.datasources.remote.RemoteDatasource
import com.timife.githubapp.data.mappers.toListOfRepos
import com.timife.githubapp.data.mappers.toListOfUsers
import com.timife.githubapp.data.mappers.toUserProfile
import com.timife.githubapp.domain.entities.repos.Repo
import com.timife.githubapp.domain.entities.userprofile.UserProfile
import com.timife.githubapp.domain.entities.users.User
import com.timife.githubapp.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val remoteDatasource: RemoteDatasource
) : UserRepository {
    override fun getUsers(query: String): Flow<List<User>> {
        return flow {
            try {
                val response = remoteDatasource.searchUsers(query)
                response.body()?.let { searchResponse ->
                    searchResponse.userDtos.toListOfUsers()
                        .let { emit(it) }
                }
            } catch (e: Exception) {
                throw RuntimeException(e.localizedMessage)
            }
        }
    }

    override fun getUserProfile(user: String): Flow<UserProfile> {
        return flow {
            try {
                val response = remoteDatasource.getUserProfile(user)
                response.body()?.let { userProfileDto ->
                    emit(userProfileDto.toUserProfile())
                }
            } catch (e: Exception) {
                throw RuntimeException(e.localizedMessage)
            }
        }
    }

    override fun getRepos(user: String): Flow<List<Repo>> {
        return flow {
            try {
                val response = remoteDatasource.getUserRepos(user)
                response.body()?.let { userRepos ->
                    emit(userRepos.toListOfRepos())
                }
            } catch (e: Exception) {
                throw RuntimeException(e.message)
            }
        }
    }

}