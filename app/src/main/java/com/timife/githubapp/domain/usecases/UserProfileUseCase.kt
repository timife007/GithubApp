package com.timife.githubapp.domain.usecases

import com.timife.githubapp.domain.Result
import com.timife.githubapp.domain.model.userprofile.UserProfile
import com.timife.githubapp.domain.model.users.User
import com.timife.githubapp.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(param1: String): Flow<UserProfile> {
        return userRepository.getUserProfile(param1)
    }
}