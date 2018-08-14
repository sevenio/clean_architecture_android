package com.example.data.store

import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import kotlin.test.assertEquals

class ProjectDataStoreFactoryTest {

    private val cacheDataStore = mock<ProjectsCacheDataStore>()
    private val remoteDataStore = mock<ProjectsRemoteDataStore>()
    private val factory = ProjectsDataStoreFactory(cacheDataStore, remoteDataStore)
    @Test
    fun getDataStoreReturnsRemoteDataStoreWhenCacheExpired() {
        assertEquals(remoteDataStore, factory.getDataStore(true, true))
    }
    @Test
    fun getDataStoreReturnsRemoteDataStoreWhenProjectsNotCached() {
        assertEquals(remoteDataStore, factory.getDataStore(false, false))
    }
    @Test
    fun getDataStoreReturnsCacheStore() {
        assertEquals(cacheDataStore, factory.getDataStore(true, false))
    }
    @Test
    fun getCacheDataStoreReturnsCacheStore() {
        assertEquals(cacheDataStore, factory.getCacheDataStore())
    }
}