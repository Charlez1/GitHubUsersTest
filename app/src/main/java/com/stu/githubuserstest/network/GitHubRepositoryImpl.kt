package com.stu.githubuserstest.network

import com.stu.githubuserstest.entity.UserDataInfo
import com.stu.githubuserstest.entity.UserListItem
import com.stu.githubuserstest.features.GitHubRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import javax.inject.Inject

class GitHubRepositoryImpl @Inject constructor(
    private val apiService: GithubApiService
) : GitHubRepository {

    override suspend fun getUserList(): List<UserListItem>  {
        val loginList = apiService.getAllUsers()
        val userList = loginList.map { loginItem ->
            apiService.getUserByLogin(loginItem.login).toUserListItem()
        }
        return userList
    }

    override suspend fun getUserInfoByLogin(login: String): UserDataInfo {
        return apiService.getUserByLogin(login).toUserDataInfo()
    }

    override suspend fun getFollowersByLogin(login: String): List<UserListItem>  {
        val loginList = apiService.getFollowersByLogin(login)
        val userList = loginList.map { loginItem ->
            apiService.getUserByLogin(loginItem.login).toUserListItem()
        }
        return userList
    }

}