package com.submission.storyapplication.domain.useCase

import com.submission.storyapplication.domain.models.AllStoriesModel
import kotlinx.coroutines.flow.Flow

interface FavoriteUseCase {
    fun getAllStoriesFavorite(): Flow<List<AllStoriesModel.stories>>
    suspend fun insertFavorite(stories: AllStoriesModel.stories)
    suspend fun  deleteFavorite(stories: AllStoriesModel.stories)
}