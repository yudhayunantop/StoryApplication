package com.submission.storyapplication.core.domain.repoInterface

import com.submission.storyapplication.core.data.remote.response.AllStoriesModel

interface IFavoriteRerpository {
    fun getAllStoriesFavorite(): List<AllStoriesModel.stories>
    suspend fun insertFavorite(stories: AllStoriesModel.stories)
    suspend fun  deleteFavorite(stories: AllStoriesModel.stories)
}