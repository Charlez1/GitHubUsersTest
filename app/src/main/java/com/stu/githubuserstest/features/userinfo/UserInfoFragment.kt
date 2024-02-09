package com.stu.githubuserstest.features.userinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.stu.githubuserstest.R
import com.stu.githubuserstest.databinding.FragmentUserInfoBinding
import com.stu.githubuserstest.features.userlist.UserListActionListener
import com.stu.githubuserstest.features.userlist.UserListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class UserInfoFragment : Fragment(R.layout.fragment_user_info), UserListActionListener {

    private val viewModel by viewModels<UserInfoViewModel>()
    private lateinit var binding: FragmentUserInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        val adapter = UserListAdapter(this)
        val login: String? = arguments?.let {
            UserInfoFragmentArgs.fromBundle(it).login
        }

        login?.let {
            viewModel.loadUserInfo(login)
        }
        observeUserInfo()
        observeUserFollowers(adapter)
        binding.btnBack.setOnClickListener { onBackButtonClicked() }
        setUpAdapter(adapter)

        return binding.root
    }


    private fun observeUserInfo() = lifecycleScope.launch(Dispatchers.Default) {

        viewModel.userInfo.collect { user ->
            withContext(Dispatchers.Main) {
                binding.login.text = user?.login
                binding.name.text = user?.name
                binding.company.text = user?.company ?: ""
                binding.email.text = user?.email ?: ""
                binding.blog.text = user?.blog ?: ""
                binding.location.text = user?.location ?: ""
                binding.bio.text = user?.bio ?: ""
                Glide.with(requireContext()).load(user?.imageUrl).into(binding.userImage)
            }
        }
    }

    private fun observeUserFollowers(adapter: UserListAdapter) = lifecycleScope.launch(Dispatchers.Default) {
        viewModel.followerList.collect { list ->
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

    private fun onBackButtonClicked() {
        findNavController().popBackStack()
    }

    override fun navigateToUserInfo(login: String) {
        val action = UserInfoFragmentDirections.actionUserInfoFragmentSelf(login)
        findNavController().navigate(action)
    }
}