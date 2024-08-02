package com.timife.githubapp.domain.usecases

import com.timife.githubapp.domain.model.users.User
import com.timife.githubapp.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(param1: String): Flow<List<User>> {
        return userRepository.getUsers(param1)
    }
}