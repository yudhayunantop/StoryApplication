package com.submission.storyapplication.core.domain.useCase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel
import kotlinx.coroutines.flow.Flow

interface AllStoriesUseCase {
    fun get_all_stories(): LiveData<PagingData<StoriesEntity>>
    fun getAllStories(): Flow<PagingData<StoriesEntity>>
}