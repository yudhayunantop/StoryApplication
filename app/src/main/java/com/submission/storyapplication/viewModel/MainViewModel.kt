package com.submission.storyapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.submission.storyapplication.core.domain.models.AllStoriesModel
import com.submission.storyapplication.core.domain.useCase.AllStoriesUseCase
import com.submission.storyapplication.core.repository.StoriesRepository

class MainViewModel(storiesRepository: com.submission.storyapplication.core.domain.useCase.AllStoriesUseCase) : ViewModel() {
    val stories: LiveData<PagingData<com.submission.storyapplication.core.domain.models.AllStoriesModel.stories>> =
        storiesRepository.get_all_stories().cachedIn(viewModelScope)
}