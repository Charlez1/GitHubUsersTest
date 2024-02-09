package com.stu.githubuserstest.di

import com.stu.githubuserstest.features.GitHubRepository
import com.stu.githubuserstest.network.GitHubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGitHubRepository(
        gitHubRepositoryImpl: GitHubRepositoryImpl
    ): GitHubRepository

}