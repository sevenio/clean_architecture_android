package com.example.cache.dao

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import com.example.cache.db.ProjectsDatabase
import com.example.cache.test.factory.ProjectDataFactory
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class CachedProjectsDaoTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext,
            ProjectsDatabase::class.java
    )
            .allowMainThreadQueries()
            .build()

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun getProjectsReturnsData() {
        val project = ProjectDataFactory.makeCachedProject()
        database.cachedProjectsDao().insertProjects(listOf(project))
        val testObserver = database.cachedProjectsDao().getProjects().test()
        testObserver.assertValue(listOf(project))
    }

    @Test
    fun deleteProjectsClearsData() {
        val project = ProjectDataFactory.makeCachedProject()
        database.cachedProjectsDao().insertProjects(listOf(project))
        database.cachedProjectsDao().deleteProjects()
        val testObserver = database.cachedProjectsDao().getProjects().test()
        testObserver.assertValue(emptyList())
    }

    @Test
    fun getBookmarkProjectsReturnsData() {
        val nonBookmarkedProject = ProjectDataFactory.makeNonBookmarkedCacheProject()
        val bookmarkedProject = ProjectDataFactory.makeBookmarkedCacheProject()

        database.cachedProjectsDao().insertProjects(listOf(nonBookmarkedProject, bookmarkedProject))
        val testObserver = database.cachedProjectsDao().getBookmarkedProjects().test()
        testObserver.assertValue(listOf(bookmarkedProject))
    }

    @Test
    fun setProjectAsBookmarkedSavesData() {
        val project = ProjectDataFactory.makeCachedProject()

        database.cachedProjectsDao().insertProjects(listOf(project))
        database.cachedProjectsDao().setBookmarkStatus(true, projectId = project.id)
        project.isBookmarked = true
        val testObserver = database.cachedProjectsDao().getBookmarkedProjects().test()
        testObserver.assertValue(listOf(project))
    }

    @Test
    fun setProjectAsNotBookmarkedSavesData() {
        val project = ProjectDataFactory.makeBookmarkedCacheProject()

        database.cachedProjectsDao().insertProjects(listOf(project))
        database.cachedProjectsDao().setBookmarkStatus(false, projectId = project.id)
        project.isBookmarked = false
        val testObserver = database.cachedProjectsDao().getBookmarkedProjects().test()
        testObserver.assertValue(emptyList())
    }
}