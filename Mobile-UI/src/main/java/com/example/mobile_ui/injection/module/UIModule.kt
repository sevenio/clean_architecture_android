package com.example.mobile_ui.injection.module

import com.example.domain.executor.PostExecutionThread
import com.example.mobile_ui.browse.BrowseActivity
import com.example.mobile_ui.UIThread
import com.example.mobile_ui.bookmarked.BookmarkedActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UIModule {
    @Binds
    abstract fun bindPostExecutionThread(uiThread: UIThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesBrowseActivity(): BrowseActivity

    @ContributesAndroidInjector
    abstract fun contributesBookmarkedActivity(): BookmarkedActivity
}