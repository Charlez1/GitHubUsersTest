package com.stu.githubuserstest.network

import com.stu.githubuserstest.network.model.UserDataResponse
import com.stu.githubuserstest.network.model.UserLoginResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

    @GET("users")
    suspend fun getAllUsers(
        @Query("per_page") size: Int = ApiConstants.LOAD_LIMIT
    ): List<UserLoginResponse>

    @GET("users/{login}")
    suspend fun getUserByLogin(
        @Path("login") login: String
    ): UserDataResponse

    @GET("users/{login}/followers")
    suspend fun getFollowersByLogin(
        @Path("login") login: String,
        @Query("per_page") size: Int = ApiConstants.LOAD_LIMIT
    ): List<UserLoginResponse>

}