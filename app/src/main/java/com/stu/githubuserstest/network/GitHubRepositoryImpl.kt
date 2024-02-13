package com.stu.githubuserstest.network

import com.stu.githubuserstest.entity.UserDataInfo
import com.stu.githubuserstest.entity.UserListItem
import com.stu.githubuserstest.features.GitHubRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GitHubRepositoryImpl @Inject constructor(
    private val apiService: GithubApiService
) : GitHubRepository {

    override suspend fun getUserList(): Flow<List<UserListItem>> = flow {
        val loginList = apiService.getAllUsers()
        loginList.forEach { loginItem ->
            val user = apiService.getUserByLogin(loginItem.login)
            val userListItem = user.toUserListItem()
            emit(listOf(userListItem))
        }
    }

    override suspend fun getUserInfoByLogin(login: String): UserDataInfo {
        return apiService.getUserByLogin(login).toUserDataInfo()
    }

    override suspend fun getFollowersByLogin(login: String): Flow<List<UserListItem>> = flow {
        val loginList = apiService.getFollowersByLogin(login)
        loginList.forEach { loginItem ->
            val user = apiService.getUserByLogin(loginItem.login)
            val userListItem = user.toUserListItem()
            emit(listOf(userListItem))
        }
    }
}