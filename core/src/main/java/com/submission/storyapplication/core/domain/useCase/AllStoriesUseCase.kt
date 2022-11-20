package com.submission.storyapplication.core.domain.useCase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.submission.storyapplication.core.domain.models.AllStoriesModel

interface AllStoriesUseCase {
    fun get_all_stories(): LiveData<PagingData<com.submission.storyapplication.core.domain.models.AllStoriesModel.stories>>
}