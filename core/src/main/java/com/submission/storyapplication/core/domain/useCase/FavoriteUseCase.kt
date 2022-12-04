package com.submission.storyapplication.core.domain.useCase

import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow

interface FavoriteUseCase {
    fun getAllStoriesFavorite(): Flow<Resources<List<StoriesEntity>>>
    suspend fun insertFavorite(stories: StoriesEntity):Flow<Resources<String>>
    suspend fun  deleteFavorite(stories: StoriesEntity): Flow<Resources<String>>
    fun isRowExist(id:String): Flow<Resources<Boolean>>
}