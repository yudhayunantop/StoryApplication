package com.submission.storyapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.submission.storyapplication.domain.models.AllStoriesModel
import com.submission.storyapplication.domain.useCase.AllStoriesUseCase
import com.submission.storyapplication.repository.StoriesRepository

class MainViewModel(storiesRepository: AllStoriesUseCase) : ViewModel() {
    val stories: LiveData<PagingData<AllStoriesModel.stories>> =
        storiesRepository.get_all_stories().cachedIn(viewModelScope)
}