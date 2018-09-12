package com.example.remote

import com.example.data.model.ProjectEntity
import com.example.remote.mapper.ProjectResponseModelMapper
import com.example.remote.model.ProjectModel
import com.example.remote.model.ProjectResponseModel
import com.example.remote.service.GithubTrendingService
import com.example.remote.test.factory.ProjectDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProjectRemoteImplTest {
    private val mapper = mock<ProjectResponseModelMapper>()
    private val service = mock<GithubTrendingService>()
    private val remote = ProjectRemoteImpl(mapper = mapper, service = service)
    @Test
    fun getProjectsCompletes() {
        stubGitHubTrendingServiceSearchRepositories(Observable.just(ProjectDataFactory.makeProjectsResponse()))
        stubProjectsResponseModelMapperMapFromModel(any(), ProjectDataFactory.makeProjectEntity())
        val testObserver = remote.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsCallsServer() {
        stubGitHubTrendingServiceSearchRepositories(Observable.just(ProjectDataFactory.makeProjectsResponse()))
        stubProjectsResponseModelMapperMapFromModel(any(), ProjectDataFactory.makeProjectEntity())
        remote.getProjects().test()
        verify(service).searchRepositories(any(), any(), any())
    }

    @Test
    fun getProjectsReturnsData() {
        val response = ProjectDataFactory.makeProjectsResponse()
        stubGitHubTrendingServiceSearchRepositories(Observable.just(response))
        val entities = mutableListOf<ProjectEntity>()
        response.items.forEach {
            val entity = ProjectDataFactory.makeProjectEntity()
            entities.add(entity)
            stubProjectsResponseModelMapperMapFromModel(it, entity)
        }
        val testObserver = remote.getProjects().test()
        testObserver.assertValue(entities)
    }

    @Test
    fun getProjectsCallsServerWithCorrectParameters() {
        stubGitHubTrendingServiceSearchRepositories(Observable.just(ProjectDataFactory.makeProjectsResponse()))
        stubProjectsResponseModelMapperMapFromModel(any(), ProjectDataFactory.makeProjectEntity())
        remote.getProjects().test()
        verify(service).searchRepositories("language:kotlin", "stars", "desc")
    }

    private fun stubProjectsResponseModelMapperMapFromModel(model: ProjectModel, entity: ProjectEntity) {
        whenever(mapper.mapFromModel(model)).thenReturn(entity)
    }

    private fun stubGitHubTrendingServiceSearchRepositories(observable: Observable<ProjectResponseModel>) {
        whenever(service.searchRepositories(any(), any(), any())).thenReturn(observable)
    }
}