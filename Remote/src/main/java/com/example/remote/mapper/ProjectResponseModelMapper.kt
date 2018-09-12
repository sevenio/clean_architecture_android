package com.example.remote.mapper

import com.example.data.model.ProjectEntity
import com.example.remote.model.ProjectModel

class ProjectResponseModelMapper : ModelMapper<ProjectModel, ProjectEntity> {
    override fun mapFromModel(model: ProjectModel): ProjectEntity {
        return ProjectEntity(model.id, model.name, model.fullName, model.startCount.toString(), model.dateCreated, model.owner.ownerName, model.owner.ownerAvatar)
    }
}