package com.example.data.test.factory

import com.example.data.model.ProjectEntity
import com.example.domain.model.Project
import javax.xml.crypto.Data

object ProjectFactory {
    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(DataFactory.randomString()
                , DataFactory.randomString()
                , DataFactory.randomString()
                , DataFactory.randomString()
                , DataFactory.randomString()
                , DataFactory.randomString()
                , DataFactory.randomString(),
                DataFactory.randomBoolean())
    }

    fun makeProject(): Project {
        return Project(DataFactory.randomString(), DataFactory.randomString()
                , DataFactory.randomString()
                , DataFactory.randomString()
                , DataFactory.randomString()
                , DataFactory.randomString()
                , DataFactory.randomString()
                , DataFactory.randomBoolean())
    }
}