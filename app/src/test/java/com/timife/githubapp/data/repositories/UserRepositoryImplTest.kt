package com.timife.githubapp.data.repositories

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.timife.githubapp.data.datasources.remote.RemoteDatasource
import com.timife.githubapp.data.mappers.toListOfRepos
import com.timife.githubapp.data.model.users.SearchResponse
import com.timife.githubapp.data.model.users.UserDto
import com.timife.githubapp.domain.entities.users.User
import com.timife.githubapp.domain.repositories.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response


@OptIn(ExperimentalCoroutinesApi::class)
class UserRepositoryImplTest {

    private lateinit var repository: UserRepositoryImpl
    private lateinit var remoteDatasource: RemoteDatasource
    @Before
    fun setUp() {
        remoteDatasource = mockk(relaxed = true){
            coEvery {
                searchUsers(TestData.QUERY)
            } returns Response.success(TestData.userApiResponse)

            coEvery {
                getUserProfile(TestData.QUERY)
            } returns Response.success(TestData.fakeUserProfileDto)

            coEvery {
                getUserRepos(TestData.QUERY)
            } returns Response.success(TestData.fakeUserRepoDto)
        }
        repository = UserRepositoryImpl(remoteDatasource)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getUsers() = runTest {
        repository.getUsers(TestData.QUERY).test {
            val response = awaitItem()
            assertThat(response).isNotEmpty()
            assertThat(response).isEqualTo(TestData.expectedEmittedUsers)
            awaitComplete()
        }
    }

    @Test
    fun getUserProfile() = runTest {
        repository.getUserProfile(TestData.QUERY).test {
            val response = awaitItem()
            assertThat(response).isNotNull()
            assertThat(response).isEqualTo(TestData.expectedEmittedProfile)
            awaitComplete()
        }
    }

    @Test
    fun getRepos() = runTest{
        repository.getRepos(TestData.QUERY).test {
            val response = awaitItem()
            assertThat(response).isNotEmpty()
            assertThat(response).isEqualTo(TestData.fakeUserRepoDto.toListOfRepos())
            awaitComplete()
        }
    }
}

