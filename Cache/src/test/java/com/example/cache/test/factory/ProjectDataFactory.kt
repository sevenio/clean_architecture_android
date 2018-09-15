package com.example.cache.test.factory

import com.example.cache.model.CachedProject
import com.example.data.model.ProjectEntity

object ProjectDataFactory {

    fun makeCachedProject(): CachedProject {
        return CachedProject(DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomBoolean())
    }

    fun makeBookmarkedCacheProject():CachedProject{
        return CachedProject(DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                true)
    }
    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomBoolean())
    }
}