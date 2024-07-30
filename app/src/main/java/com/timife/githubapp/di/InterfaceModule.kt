package com.timife.githubapp.di

import com.timife.githubapp.data.repositories.FollowersRepositoryImpl
import com.timife.githubapp.data.repositories.UserRepositoryImpl
import com.timife.githubapp.domain.repositories.FollowersRepository
import com.timife.githubapp.domain.repositories.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InterfaceModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepoImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindFollowersRepository(
        followersRepoImpl: FollowersRepositoryImpl
    ): FollowersRepository

}