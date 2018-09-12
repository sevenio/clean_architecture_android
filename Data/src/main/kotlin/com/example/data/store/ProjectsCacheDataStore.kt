package com.example.data.store

import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectsCache
import com.example.data.repository.ProjectsDataStore
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

/**
 *
 */
class ProjectsCacheDataStore @Inject constructor(private val projectsCache: ProjectsCache) : ProjectsDataStore {
    override fun clearProjects(): Completable {
        return projectsCache.clearProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return projectsCache.saveProjects(projects).andThen(projectsCache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsCache.getProjects()
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectsCache.getBookmarkedProjects()
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return projectsCache.setProjectAsBookmarked(projectId)
    }

    override fun setProjectAsUnBookmarked(projectId: String): Completable {
        return projectsCache.setProjectAsUnbookmarked(projectId)
    }
}