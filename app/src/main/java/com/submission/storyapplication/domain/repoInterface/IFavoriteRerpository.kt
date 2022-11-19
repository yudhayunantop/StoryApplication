package com.submission.storyapplication.domain.repoInterface

import com.submission.storyapplication.domain.models.AllStoriesModel
import kotlinx.coroutines.flow.Flow

interface IFavoriteRerpository {
    fun getAllStoriesFavorite(): List<AllStoriesModel.stories>
    suspend fun insertFavorite(stories: AllStoriesModel.stories)
    suspend fun  deleteFavorite(stories: AllStoriesModel.stories)
}