package com.stu.githubuserstest.features.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stu.githubuserstest.R
import com.stu.githubuserstest.databinding.UserItemBinding
import com.stu.githubuserstest.entity.UserListItem

interface UserListActionListener {

    fun navigateToUserInfo(login: String)
}

class UserListAdapter(
    private val actionListener:UserListActionListener
): RecyclerView.Adapter<UserListAdapter.UserListViewHolder>(), View.OnClickListener {

    override fun onClick(view: View) {
        val login = view.tag as String
        actionListener.navigateToUserInfo(login)
    }

    var userList: List<UserListItem> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserItemBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return UserListViewHolder(binding)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val user = userList[position]
        holder.itemView.tag = user.login
        holder.bind(user)
    }

    inner class UserListViewHolder(
        private val binding: UserItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserListItem) {
            binding.login.text = user.login
            binding.followers.text = this.itemView.context.getString(R.string.followers_count, user.followersCount)
            binding.repos.text = this.itemView.context.getString(R.string.repositories_count, user.repositoryCount)
            Glide.with(this.itemView.context).load(user.imageUrl).into(binding.userImage)
        }
    }
}