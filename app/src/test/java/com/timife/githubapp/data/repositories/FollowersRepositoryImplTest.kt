package com.timife.githubapp.data.repositories

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.timife.githubapp.data.datasources.remote.RemoteDatasource
import com.timife.githubapp.data.mappers.toListOfUsers
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class FollowersRepositoryImplTest {

    private lateinit var repository: FollowersRepositoryImpl
    private lateinit var remoteDatasource: RemoteDatasource
    @Before
    fun setUp() {
        remoteDatasource = mockk(relaxed = true) {
            coEvery {
                getUserFollowers(TestData.QUERY)
            } returns Response.success(TestData.fakeFollowersApiResponse)

            coEvery {
                getUserFollowing(TestData.QUERY)
            } returns Response.success(TestData.fakeFollowersApiResponse)
        }

        repository = FollowersRepositoryImpl(remoteDatasource)
    }


    @After
    fun tearDown() {
    }

    @Test
    fun getFollowers() = runTest{
        repository.getFollowers(TestData.QUERY).test {
            val response = awaitItem()
            assertThat(response).isNotEmpty()
            assertThat(response).isEqualTo(TestData.fakeFollowersApiResponse.toListOfUsers())
            awaitComplete()
        }
    }

    @Test
    fun getFollowing() = runTest{
        repository.getFollowing(TestData.QUERY).test {
            val response = awaitItem()
            assertThat(response).isNotEmpty()
            assertThat(response).isEqualTo(TestData.fakeFollowingsApiResponse.toListOfUsers())
            awaitComplete()
        }
    }
}