package com.example.cache

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import com.example.cache.db.ProjectsDatabase
import com.example.cache.mapper.CachedProjectMapper
import com.example.cache.model.CachedProject
import com.example.cache.test.factory.ProjectDataFactory
import com.example.data.model.ProjectEntity
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class ProjectsCacheImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext,
            ProjectsDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    private val entityMapper = mock<CachedProjectMapper>()
    private val cache = ProjectsCacheImpl(database, entityMapper)

    @Test
    fun clearTablesCompletes() {
        val testObserver = cache.clearProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveProjectsCompletes() {
        val projects = listOf(ProjectDataFactory.makeProjectEntity())
        projects.forEach { stubResponseOfMappersMapToCache(it, ProjectDataFactory.makeCachedProject()) }
        val testObserver = cache.saveProjects(projects).test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val projects = listOf(ProjectDataFactory.makeProjectEntity())
        val cachedProjects = listOf(ProjectDataFactory.makeCachedProject())
        projects.forEachIndexed { index, projectEntity -> stubResponseOfMappersMapToCache(projectEntity, cachedProjects[index]) }
        cache.saveProjects(projects).test()
        cachedProjects.forEachIndexed { index, cachedProject -> stubResponseOfMappersMapFromCache(projects[index], cachedProject) }
        val testObserver = cache.getProjects().test()
        testObserver.assertValue(projects)
    }

    @Test
    fun getBookmarkedProjectsReturnsData() {
        val bookmarkedProject = ProjectDataFactory.makeBookmarkedProjectEntity()
        val unBookmarkedProject = ProjectDataFactory.makeNonBookmarkedProjectEntity()

        val bookUnbookmarkedProjects = listOf(bookmarkedProject, unBookmarkedProject)

        val bookmarkedCachedProject = ProjectDataFactory.makeBookmarkedCacheProject()
        val unbookmarkedCachedProject = ProjectDataFactory.makeNonBookmarkedCacheProject()

        val bookUnbookmarkedCachedProjects = listOf(bookmarkedCachedProject, unbookmarkedCachedProject)

        bookUnbookmarkedProjects.forEachIndexed { index, projectEntity -> stubResponseOfMappersMapToCache(projectEntity, bookUnbookmarkedCachedProjects[index]) }
        cache.saveProjects(bookUnbookmarkedProjects).test()
        bookUnbookmarkedCachedProjects.forEachIndexed { index, cachedProject -> stubResponseOfMappersMapFromCache(bookUnbookmarkedProjects[index], cachedProject) }
        val testObserver = cache.getBookmarkedProjects().test()
        testObserver.assertValue(listOf(bookmarkedProject))

    }


    @Test
    fun setProjectAsBookmarkedCompletes() {
        val projects = listOf(ProjectDataFactory.makeNonBookmarkedProjectEntity())

//        projects.forEachIndexed { index, projectEntity -> stubResponseOfMappersMapToCache(projectEntity, ProjectDataFactory.makeNonBookmarkedCacheProject()) }
//
//        cache.saveProjects(projects).test()

        val testObserver = cache.setProjectAsBookmarked(projects[0].id).test()
        testObserver.assertComplete()
    }

    @Test
    fun setProjectAsNotBookmarkedCompletes() {
        val projects = listOf(ProjectDataFactory.makeBookmarkedProjectEntity())

        val testObserver = cache.setProjectAsUnbookmarked(projects[0].id).test()
        testObserver.assertComplete()
    }

    @Test
    fun areProjectsCacheReturnsData() {
        val projects = listOf(ProjectDataFactory.makeProjectEntity())
        cache.saveProjects(projects)
        val testObserver = cache.areProjectsCached().test()
        testObserver.assertValue(true)
    }

    @Test
    fun setLastCacheTimeCompletes() {
        val testObserver = cache.setLastCacheTime(1000L).test()
        testObserver.assertComplete()
    }


    @Test
    fun isProjectsCacheExpiredReturnsNotExpired() {
        cache.setLastCacheTime(System.currentTimeMillis()).test()
        val testObserver = cache.isProjectsCacheExpired().test()
        testObserver.assertValue(false)
    }

    private fun stubResponseOfMappersMapToCache(projectEntity: ProjectEntity, cachedProject: CachedProject) {
        whenever(entityMapper.mapToCache(projectEntity)).thenReturn(cachedProject)
    }

    private fun stubResponseOfMappersMapFromCache(projectEntity: ProjectEntity, cachedProject: CachedProject) {
        whenever(entityMapper.mapFromCache(cachedProject)).thenReturn(projectEntity)
    }

}