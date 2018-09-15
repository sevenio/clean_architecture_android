package com.example.cache.mapper

import com.example.cache.model.CachedProject
import com.example.cache.test.factory.ProjectDataFactory
import com.example.data.model.ProjectEntity
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CachedProjectMapperTest {

    private val mapper = CachedProjectMapper()
    @Test
    fun mapFromCachedMapsData() {
        val model = ProjectDataFactory.makeCachedProject()
        val entity = mapper.mapFromCache(model)
        assertEqualData(model, entity)
    }

    @Test
    fun mapToCachedMapsData() {
        val entity = ProjectDataFactory.makeProjectEntity()
        val model = mapper.mapToCache(entity)
        assertEqualData(model, entity)
    }

    private fun assertEqualData(model: CachedProject,
                                entity: ProjectEntity) {
        assertEquals(model.id, entity.id)

        assertEquals(model.dateCreated, entity.dateCreated)
        assertEquals(model.fullName, entity.fullName)
        assertEquals(model.isBookmarked, entity.isBookmarked)
        assertEquals(model.name, entity.name)
        assertEquals(model.ownerAvatar, entity.ownerAvatar)
        assertEquals(model.ownerName, entity.ownerName)
        assertEquals(model.starCount, entity.starCount)


    }
}