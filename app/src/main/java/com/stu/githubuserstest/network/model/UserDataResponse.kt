package com.stu.githubuserstest.network.model

import com.google.gson.annotations.SerializedName
import com.stu.githubuserstest.entity.UserDataInfo
import com.stu.githubuserstest.entity.UserListItem

data class UserDataResponse(
    @SerializedName("login") val login: String,
    @SerializedName("name") val name: String,
    @SerializedName("followers") val followersCount: Int,
    @SerializedName("public_repos") val repositoryCount: Int,
    @SerializedName("avatar_url") val imageUrl: String?,
    @SerializedName("company") val company: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("blog") val blog: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("bio") val bio: String?
) {
    fun toUserDataInfo() : UserDataInfo {
        return UserDataInfo(
            login = login,
            name = name,
            imageUrl = imageUrl,
            company = company,
            email = email,
            blog = blog,
            location = location,
            bio = bio
        )
    }

    fun toUserListItem() : UserListItem {
        return UserListItem(
            login = login,
            imageUrl = imageUrl,
            followersCount = followersCount,
            repositoryCount = repositoryCount,
        )
    }
}
