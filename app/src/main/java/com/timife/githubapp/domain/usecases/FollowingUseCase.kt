package com.timife.githubapp.domain.usecases

import com.timife.githubapp.domain.entities.users.User
import com.timife.githubapp.domain.repositories.FollowersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FollowingUseCase @Inject constructor(
    private val followersRepository: FollowersRepository
) {
    operator fun invoke(param1: String): Flow<List<User>> {
        return followersRepository.getFollowing(param1)
    }
}