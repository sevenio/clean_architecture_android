package com.example.domain.repository

import com.example.domain.model.Project
import io.reactivex.Completable
import io.reactivex.Observable
import java.util.*

interface ProjectsRepository{
    fun getProjects(): Observable<List<Project>>

    fun bookmarkProject(projectId: String): Completable

    fun unbookmarkProject(projectId: String): Completable

    fun getBookmarkedProjects(): Observable<List<Project>>

}