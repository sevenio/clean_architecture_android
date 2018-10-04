package com.example.mobile_ui.injection

import com.example.data.repository.ProjectsRemote
import com.example.remote.service.GithubTrendingService
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides

@Module
object TestRemoteModule {

    @Provides
    @JvmStatic
    fun provideGithubService(): GithubTrendingService {
        return mock()
    }

    @Provides
    @JvmStatic
    fun provideProjectsRemote(): ProjectsRemote {
        return mock()
    }

}