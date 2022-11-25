package com.submission.storyapplication.core.domain.useCase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel

interface AllStoriesUseCase {
    fun get_all_stories(): LiveData<PagingData<AllStoriesModel.stories>>
}