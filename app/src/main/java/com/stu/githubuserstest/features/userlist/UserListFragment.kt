package com.stu.githubuserstest.features.userlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.stu.githubuserstest.R
import com.stu.githubuserstest.databinding.FragmentUserListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class UserListFragment : Fragment(R.layout.fragment_user_list), UserListActionListener {

    private val viewModel by viewModels<UserListViewModel>()
    private lateinit var binding: FragmentUserListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        val adapter = UserListAdapter(this)

        viewModel.loadUserList()
        observeUserList(adapter)
        setUpAdapter(adapter)

        return binding.root
    }

    private fun observeUserList(adapter: UserListAdapter) = lifecycleScope.launch(Dispatchers.Default) {
        viewModel.userList.collect { list ->
            withContext(Dispatchers.Main) {
                adapter.userList = list
            }
        }
    }

    private fun setUpAdapter(adapter: UserListAdapter) {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.list.layoutManager = layoutManager
        binding.list.adapter = adapter
    }

    override fun navigateToUserInfo(login: String) {
        val action = UserListFragmentDirections.actionUserListFragmentToUserInfoFragment(login)
        findNavController().navigate(action)
    }
}