package com.timife.githubapp.data.repositories

import com.timife.githubapp.data.datasources.remote.RemoteDatasource
import com.timife.githubapp.data.mappers.toListOfUsers
import com.timife.githubapp.data.mappers.toUserProfile
import com.timife.githubapp.domain.Result
import com.timife.githubapp.domain.model.users.User
import com.timife.githubapp.domain.repositories.FollowersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FollowersRepositoryImpl @Inject constructor(
    private val remoteDatasource: RemoteDatasource
) : FollowersRepository {
    override fun getFollowers(user:String): Flow<List<User>> {
        return flow {
            try {
                val response = remoteDatasource.getUserFollowers(user)
                response.body()?.let {followersDto ->
                    emit(followersDto.toListOfUsers())
                }
            }catch (e:Exception){
                throw RuntimeException(e.localizedMessage)
            }
        }
    }

    override fun getFollowing(user: String): Flow<List<User>>{
        return flow {
            try {
                val response = remoteDatasource.getUserFollowing(user)
                response.body()?.let {following ->
                    emit(following.toListOfUsers())
                }
            }catch (e:Exception){
                throw RuntimeException(e.localizedMessage)
            }
        }
    }


}