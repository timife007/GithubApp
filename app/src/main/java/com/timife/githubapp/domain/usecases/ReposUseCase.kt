package com.timife.githubapp.domain.usecases

import com.timife.githubapp.domain.Result
import com.timife.githubapp.domain.model.repos.Repo
import com.timife.githubapp.domain.model.users.User
import com.timife.githubapp.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReposUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(param1: String): Flow<Result<List<Repo>>> {
        return userRepository.getRepos(param1)
    }
}