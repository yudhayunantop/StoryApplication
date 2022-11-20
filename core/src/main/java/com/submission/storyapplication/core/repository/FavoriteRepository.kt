package com.submission.storyapplication.core.repository

import com.submission.storyapplication.core.database.StoryDao
import com.submission.storyapplication.core.domain.models.AllStoriesModel
import com.submission.storyapplication.core.domain.repoInterface.IFavoriteRerpository
import kotlinx.coroutines.flow.Flow

class FavoriteRepository(val storyDao: com.submission.storyapplication.core.database.StoryDao):
    com.submission.storyapplication.core.domain.repoInterface.IFavoriteRerpository {
    override fun getAllStoriesFavorite(): List<com.submission.storyapplication.core.domain.models.AllStoriesModel.stories> {
        return storyDao.getAllStoriesFavorite()
    }

    override suspend fun insertFavorite(stories: com.submission.storyapplication.core.domain.models.AllStoriesModel.stories) {
        return storyDao.insertFavorite(stories)
    }

    override suspend fun deleteFavorite(stories: com.submission.storyapplication.core.domain.models.AllStoriesModel.stories) {
        return storyDao.deleteFavorite(stories)
    }
}