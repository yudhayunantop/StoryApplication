package com.submission.storyapplication.core.domain.repoInterface

import com.submission.storyapplication.core.data.remote.response.AllStoriesModel
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow

interface IFavoriteRepository {
    fun getAllStoriesFavorite(): Flow<Resources<List<AllStoriesModel.stories>>>
    suspend fun insertFavorite(stories: AllStoriesModel.stories): Flow<Resources<String>>
    suspend fun  deleteFavorite(stories: AllStoriesModel.stories): Flow<Resources<String>>
    fun isRowExist(id:String): Flow<Resources<Boolean>>
}