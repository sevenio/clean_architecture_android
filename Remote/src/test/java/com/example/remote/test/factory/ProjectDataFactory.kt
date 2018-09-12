package com.example.remote.test.factory

import com.example.data.model.ProjectEntity
import com.example.remote.model.OwnerModel
import com.example.remote.model.ProjectModel
import com.example.remote.model.ProjectResponseModel

object ProjectDataFactory {

    fun makeOwner(): OwnerModel {
        return OwnerModel(DataFactory.randomUuid(), DataFactory.randomUuid())
    }

    fun makeProject(): ProjectModel {
        return ProjectModel(DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomInt(), DataFactory.randomUuid(), makeOwner())
    }

    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomBoolean())
    }

    fun makeProjectsResponse(): ProjectResponseModel {
        return ProjectResponseModel(listOf(makeProject(), makeProject()))
    }
}