package com.example.mobile_ui.injection

import com.example.domain.repository.ProjectsRepository
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object TestDataModule {

    @Provides
    @JvmStatic
    @Singleton
    fun provideDataRepository(): ProjectsRepository {
        return mock()
    }

}