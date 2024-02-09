package com.stu.githubuserstest.features

import com.stu.githubuserstest.entity.UserDataInfo
import com.stu.githubuserstest.entity.UserListItem

interface GitHubRepository {

    suspend fun getUserList(): List<UserListItem>

    suspend fun getUserInfoByLogin(login: String): UserDataInfo

    suspend fun getFollowersByLogin(login: String): List<UserListItem>
}