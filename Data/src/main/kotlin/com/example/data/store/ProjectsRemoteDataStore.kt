package com.example.data.store

import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectsDataStore
import com.example.data.repository.ProjectsRemote
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsRemoteDataStore @Inject constructor(private val projectsRemote: ProjectsRemote) : ProjectsDataStore {
    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        throw UnsupportedOperationException("")
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("")
    }

    override fun setProjectAsUnBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("")
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        throw UnsupportedOperationException("")
    }

    override fun clearProjects(): Completable {
        throw UnsupportedOperationException("")
    }

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsRemote.getProjects()
    }
}