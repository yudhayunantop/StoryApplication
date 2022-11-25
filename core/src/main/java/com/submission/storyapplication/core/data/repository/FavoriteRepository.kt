package com.submission.storyapplication.core.data.repository

import com.submission.storyapplication.core.domain.repoInterface.IFavoriteRerpository
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel
import com.submission.storyapplication.core.data.database.StoryDao

class FavoriteRepository(val storyDao: StoryDao):
    IFavoriteRerpository {
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