package com.stu.githubuserstest.features.userinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stu.githubuserstest.entity.UserDataInfo
import com.stu.githubuserstest.entity.UserListItem
import com.stu.githubuserstest.features.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val repository: GitHubRepository
) : ViewModel() {

    private val _followerList = MutableStateFlow<List<UserListItem>>(emptyList())
    val followerList: StateFlow<List<UserListItem>> = _followerList.asStateFlow()

    private val _userInfo = MutableStateFlow<UserDataInfo?>(null)
    val userInfo: StateFlow<UserDataInfo?> = _userInfo.asStateFlow()

    fun loadUserInfo(login: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getFollowersByLogin(login).collect {
                    _followerList.update { currentList ->
                        currentList + it
                    }
                }
            } catch (e: Exception) {
                //todo
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userInfo = repository.getUserInfoByLogin(login)
                _userInfo.value = userInfo
            } catch (e: Exception) {
                //todo
            }
        }
    }
}