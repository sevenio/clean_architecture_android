package com.example.domain.interactor.browse

import com.example.domain.executor.PostExecutionThread
import com.example.domain.interactor.ObservableUseCase
import com.example.domain.model.Project
import com.example.domain.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetProjects @Inject constructor(private val projectsRepository: ProjectsRepository, postExecutionThread: PostExecutionThread): ObservableUseCase<List<Project>, Nothing>(postExecutionThread){
    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getProjects()
    }

}