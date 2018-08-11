package com.example.domain.interactor.bookmark

import com.example.domain.executor.PostExecutionThread
import com.example.domain.repository.ProjectsRepository
import com.example.domain.test.ProjectDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UnbookmarkProjectTest {
    @Mock
    lateinit var projectsRepository: ProjectsRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread
    private lateinit var unBookmarkProject: UnBookmarkProject

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        unBookmarkProject = UnBookmarkProject(projectsRepository, postExecutionThread)
    }

    private fun getStubProjects(completable: Completable) {
        whenever(projectsRepository.unbookmarkProject(any())).thenReturn(completable)
    }

    @Test
    fun unbookmarkProjectCompletes() {
        getStubProjects(Completable.complete())
        val testObserver = unBookmarkProject.buildUseCaseCompletable(UnBookmarkProject.Params.forProject(ProjectDataFactory.randomUuid())).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun unbookmarkProjectThrowsException() {
        unBookmarkProject.buildUseCaseCompletable().test()
    }

}