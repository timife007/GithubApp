package com.timife.githubapp.data.repositories

import com.timife.githubapp.data.datasources.remote.RemoteDatasource
import com.timife.githubapp.data.mappers.toListOfRepos
import com.timife.githubapp.data.mappers.toListOfUsers
import com.timife.githubapp.data.mappers.toUserProfile
import com.timife.githubapp.domain.Result
import com.timife.githubapp.domain.model.repos.Repo
import com.timife.githubapp.domain.model.userprofile.UserProfile
import com.timife.githubapp.domain.model.users.User
import com.timife.githubapp.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val remoteDatasource: RemoteDatasource
) : UserRepository {
    override fun getUsers(query: String): Flow<Result<List<User>>> {
        return flow {
            try {
                val response = remoteDatasource.searchUsers(query)
                response.body()?.let {userDtos ->
                    emit(Result.Success(userDtos.toListOfUsers()))
                }
            }catch (e:Exception){
                emit(Result.Error(e))
            }
        }
    }

    override fun getUserProfile(user:String): Flow<Result<UserProfile>> {
        return flow {
            try {
                val response = remoteDatasource.getUserProfile(user)
                response.body()?.let {userProfileDto ->
                    emit(Result.Success(userProfileDto.toUserProfile()))
                }
            }catch (e:Exception){
                emit(Result.Error(e))
            }
        }
    }

    override fun getRepos(user:String): Flow<Result<List<Repo>>> {
        return flow {
            try {
                val response = remoteDatasource.getUserRepos(user)
                response.body()?.let {userRepos ->
                    emit(Result.Success(userRepos.toListOfRepos()))
                }
            }catch (e:Exception){
                emit(Result.Error(e))
            }
        }
    }

}