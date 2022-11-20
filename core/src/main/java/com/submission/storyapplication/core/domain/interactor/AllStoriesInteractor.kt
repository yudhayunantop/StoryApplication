package com.submission.storyapplication.core.domain.interactor

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.submission.storyapplication.core.domain.models.AllStoriesModel
import com.submission.storyapplication.core.domain.repoInterface.IAllStoriesRepository
import com.submission.storyapplication.core.domain.useCase.AllStoriesUseCase

class AllStoriesInteractor(val storiesRepository: com.submission.storyapplication.core.domain.repoInterface.IAllStoriesRepository):
    com.submission.storyapplication.core.domain.useCase.AllStoriesUseCase {
    override fun get_all_stories(): LiveData<PagingData<com.submission.storyapplication.core.domain.models.AllStoriesModel.stories>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                storiesRepository.getPagingSource()
            }
        ).liveData
    }
}