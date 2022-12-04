package com.submission.storyapplication.core.domain.repoInterface

import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import com.submission.storyapplication.core.data.remote.network.ApiResponse
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow

interface IMapsRepository {
    suspend fun get_all_stories_location(token: String): Flow<ApiResponse<List<StoriesEntity>>>
}