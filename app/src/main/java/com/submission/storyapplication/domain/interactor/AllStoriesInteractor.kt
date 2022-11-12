package com.submission.storyapplication.domain.interactor

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.submission.storyapplication.domain.models.AllStoriesModel
import com.submission.storyapplication.domain.repoInterface.IAllStoriesRepository
import com.submission.storyapplication.domain.useCase.AllStoriesUseCase

class AllStoriesInteractor(val storiesRepository: IAllStoriesRepository): AllStoriesUseCase {
    override fun get_all_stories(): LiveData<PagingData<AllStoriesModel.stories>> {
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