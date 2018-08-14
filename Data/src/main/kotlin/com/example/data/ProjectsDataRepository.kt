package com.example.data

import com.example.data.mapper.EntityMapper
import com.example.data.mapper.ProjectMapper
import com.example.data.repository.ProjectsCache
import com.example.data.store.ProjectsDataStoreFactory
import com.example.domain.model.Project
import com.example.domain.repository.ProjectsRepository
import com.sun.org.apache.xpath.internal.operations.Bool
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class ProjectsDataRepository @Inject constructor(
        private val mapper: ProjectMapper,
        private val cache: ProjectsCache,
        private val factory: ProjectsDataStoreFactory
) : ProjectsRepository {
    override fun getProjects(): Observable<List<Project>> {
        return Observable.zip(cache.areProjectsCached().toObservable(),
                cache.isProjectsCacheExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                })
                .flatMap {
                    factory.getDataStore(it.first, it.second).getProjects()
                }
                .flatMap { projects ->
                    factory.getCacheDataStore()
                            .saveProjects(projects)
                            .andThen(Observable.just(projects))
                }
                .map {
                    it.map { mapper.mapFromEntity(it) }
                }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsBookmarked(projectId)
    }

    override fun unbookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsUnBookmarked(projectId)
    }

    override fun getBookmarkedProjects(): Observable<List<Project>> {
        return factory.getCacheDataStore().getBookmarkedProjects()
                .map {
                    it.map {
                        mapper.mapFromEntity(it)
                    }
                }
    }
}