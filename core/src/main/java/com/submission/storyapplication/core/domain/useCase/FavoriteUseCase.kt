package com.submission.storyapplication.core.domain.useCase

import com.submission.storyapplication.core.domain.models.AllStoriesModel
import kotlinx.coroutines.flow.Flow

interface FavoriteUseCase {
    fun getAllStoriesFavorite(): List<com.submission.storyapplication.core.domain.models.AllStoriesModel.stories>
    suspend fun insertFavorite(stories: com.submission.storyapplication.core.domain.models.AllStoriesModel.stories)
    suspend fun  deleteFavorite(stories: com.submission.storyapplication.core.domain.models.AllStoriesModel.stories)
}