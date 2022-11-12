package com.submission.storyapplication.domain.useCase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.submission.storyapplication.domain.models.AllStoriesModel

interface AllStoriesUseCase {
    fun get_all_stories(): LiveData<PagingData<AllStoriesModel.stories>>
}