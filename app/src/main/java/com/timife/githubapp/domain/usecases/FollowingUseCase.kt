package com.timife.githubapp.domain.usecases

import com.timife.githubapp.domain.Result
import com.timife.githubapp.domain.model.users.User
import com.timife.githubapp.domain.repositories.FollowersRepository
import com.timife.githubapp.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FollowingUseCase @Inject constructor(
    private val followersRepository: FollowersRepository
) {
    operator fun invoke(param1: String): Flow<List<User>> {
        return followersRepository.getFollowing(param1)
    }
}