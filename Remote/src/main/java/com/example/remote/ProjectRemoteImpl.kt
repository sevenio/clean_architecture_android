package com.example.remote

import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectsRemote
import com.example.remote.mapper.ModelMapper
import com.example.remote.mapper.ProjectResponseModelMapper
import com.example.remote.model.ProjectModel
import com.example.remote.service.GitHubTrendingServiceFactory
import com.example.remote.service.GithubTrendingService
import io.reactivex.Observable
import javax.inject.Inject

class ProjectRemoteImpl @Inject constructor(
        private val service: GithubTrendingService,
        private val mapper: ProjectResponseModelMapper
) : ProjectsRemote {
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return service.searchRepositories("language:kotlin", "stars", "desc")
                .map {
                    it.items.map {
                        mapper.mapFromModel(it)
                    }
                }
    }
}