package com.timife.githubapp.domain.repositories

import com.timife.githubapp.domain.model.users.User
import kotlinx.coroutines.flow.Flow

interface FollowersRepository {

    fun getFollowers(): Flow<List<User>>

    fun getFollowing(): Flow<List<User>>
}