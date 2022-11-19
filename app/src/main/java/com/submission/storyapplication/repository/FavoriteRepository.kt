package com.submission.storyapplication.repository

import com.submission.storyapplication.database.StoryDao
import com.submission.storyapplication.domain.models.AllStoriesModel
import com.submission.storyapplication.domain.repoInterface.IFavoriteRerpository
import kotlinx.coroutines.flow.Flow

class FavoriteRepository(val storyDao: StoryDao): IFavoriteRerpository{
    override fun getAllStoriesFavorite(): List<AllStoriesModel.stories> {
        return storyDao.getAllStoriesFavorite()
    }

    override suspend fun insertFavorite(stories: AllStoriesModel.stories) {
        return storyDao.insertFavorite(stories)
    }

    override suspend fun deleteFavorite(stories: AllStoriesModel.stories) {
        return storyDao.deleteFavorite(stories)
    }
}