package com.example.remote.mapper

import com.example.remote.test.factory.ProjectDataFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class ProjectResponseModelMapperTest {
    private val mapper = ProjectResponseModelMapper()
    @Test
    fun mapFromModelMapsData() {
        val model = ProjectDataFactory.makeProject()
        val entity = mapper.mapFromModel(model)
        assertEquals(model.id, entity.id)
        assertEquals(model.name, entity.name)
        assertEquals(model.fullName, entity.fullName)
        assertEquals(model.startCount.toString(), entity.starCount)
        assertEquals(model.dateCreated, entity.dateCreated)
        assertEquals(model.owner.ownerName, entity.ownerName)
        assertEquals(model.owner.ownerAvatar, entity.ownerAvatar)


    }
}