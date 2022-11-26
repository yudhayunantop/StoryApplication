package com.submission.storyapplication.core.data.local

import com.submission.storyapplication.core.data.local.database.StoryDao
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel

class LocalDataSource(private val storyDao: StoryDao) {
    fun getAllStoriesFavorite() = storyDao.getAllStoriesFavorite()
    suspend fun insertFavorite(stories: AllStoriesModel.stories) = storyDao.insertFavorite(stories)
    suspend fun deleteFavorite(stories: AllStoriesModel.stories) = storyDao.deleteFavorite(stories)
}