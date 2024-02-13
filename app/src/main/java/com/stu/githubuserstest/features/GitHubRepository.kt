package com.stu.githubuserstest.features

import com.stu.githubuserstest.entity.UserDataInfo
import com.stu.githubuserstest.entity.UserListItem
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {

    suspend fun getUserList(): Flow<List<UserListItem>>

    suspend fun getUserInfoByLogin(login: String): UserDataInfo

    suspend fun getFollowersByLogin(login: String): Flow<List<UserListItem>> 
}