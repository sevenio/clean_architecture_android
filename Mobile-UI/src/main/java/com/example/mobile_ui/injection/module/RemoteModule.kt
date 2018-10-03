package com.example.mobile_ui.injection.module

import com.example.data.repository.ProjectsRemote
import com.example.mobile_ui.BuildConfig
import com.example.remote.ProjectRemoteImpl
import com.example.remote.service.GitHubTrendingServiceFactory
import com.example.remote.service.GithubTrendingService
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {
    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGithubService(): GithubTrendingService {
            return GitHubTrendingServiceFactory.makeGithubTrendingService(BuildConfig.DEBUG)
        }
    }
    @Binds
abstract fun bindProjectsRemote(projectsRemote: ProjectRemoteImpl): ProjectsRemote
}