package com.stu.githubuserstest.features.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class UserListViewModel @Inject constructor(
    private val repository: GitHubRepository
): ViewModel() {

    private val _userList = MutableStateFlow<List<UserListItem>>(emptyList())
    val userList: StateFlow<List<UserListItem>> = _userList.asStateFlow()

    fun loadUserList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getUserList().collect {
                    _userList.update { currentList ->
                        currentList + it
                    }
                }
            } catch (e: Exception) {
                //todo
            }
        }
    }



}