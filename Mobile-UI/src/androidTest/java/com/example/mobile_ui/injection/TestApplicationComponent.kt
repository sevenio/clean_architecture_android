package com.example.mobile_ui.injection

import android.app.Application
import com.example.domain.repository.ProjectsRepository
import com.example.mobile_ui.injection.module.PresentationModule
import com.example.mobile_ui.injection.module.UIModule
import com.example.mobile_ui.test.TestApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class,
        TestApplicationModule::class,
        TestCacheModule::class,
        TestDataModule::class,
        PresentationModule::class,
        UIModule::class,
        TestRemoteModule::class))
interface TestApplicationComponent {

    fun projectsRepository(): ProjectsRepository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): TestApplicationComponent.Builder

        fun build(): TestApplicationComponent
    }

    fun inject(application: TestApplication)

}