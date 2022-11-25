package com.submission.storyapplication.core.domain.useCase

import com.submission.storyapplication.core.data.remote.response.AllStoriesModel

interface FavoriteUseCase {
    fun getAllStoriesFavorite(): List<AllStoriesModel.stories>
    suspend fun insertFavorite(stories: AllStoriesModel.stories)
    suspend fun  deleteFavorite(stories: AllStoriesModel.stories)
}