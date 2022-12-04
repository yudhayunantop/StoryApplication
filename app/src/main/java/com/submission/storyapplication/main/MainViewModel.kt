package com.submission.storyapplication.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import com.submission.storyapplication.core.domain.useCase.AllStoriesUseCase
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel
import kotlinx.coroutines.flow.Flow

class MainViewModel(useCase: AllStoriesUseCase) : ViewModel() {
    val stories: LiveData<PagingData<StoriesEntity>> =
        useCase.get_all_stories().cachedIn(viewModelScope)

    val storiesFlow: Flow<PagingData<StoriesEntity>> = useCase.getAllStories().cachedIn(viewModelScope)
}