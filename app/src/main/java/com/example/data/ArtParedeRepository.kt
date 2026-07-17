package com.example.data

import kotlinx.coroutines.flow.Flow

class ArtParedeRepository(private val artParedeDao: ArtParedeDao) {

    val allProjects: Flow<List<MoodboardProject>> = artParedeDao.getAllProjects()

    suspend fun getProjectById(id: Int): MoodboardProject? {
        return artParedeDao.getProjectById(id)
    }

    suspend fun insertProject(project: MoodboardProject): Long {
        return artParedeDao.insertProject(project)
    }

    suspend fun deleteProject(project: MoodboardProject) {
        artParedeDao.deleteProject(project)
    }

    fun getItemsForProject(projectId: Int): Flow<List<MoodboardItem>> {
        return artParedeDao.getItemsForProject(projectId)
    }

    suspend fun insertItem(item: MoodboardItem) {
        artParedeDao.insertItem(item)
    }

    suspend fun deleteItem(item: MoodboardItem) {
        artParedeDao.deleteItem(item)
    }
}
