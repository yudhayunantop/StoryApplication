package com.submission.storyapplication.core.domain.repoInterface

import com.submission.storyapplication.core.domain.models.AllStoriesModel
import kotlinx.coroutines.flow.Flow

interface IFavoriteRerpository {
    fun getAllStoriesFavorite(): List<com.submission.storyapplication.core.domain.models.AllStoriesModel.stories>
    suspend fun insertFavorite(stories: com.submission.storyapplication.core.domain.models.AllStoriesModel.stories)
    suspend fun  deleteFavorite(stories: com.submission.storyapplication.core.domain.models.AllStoriesModel.stories)
}