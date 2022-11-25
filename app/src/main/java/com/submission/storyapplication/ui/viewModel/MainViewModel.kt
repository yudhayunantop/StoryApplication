package com.submission.storyapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.submission.storyapplication.core.domain.useCase.AllStoriesUseCase
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel

class MainViewModel(storiesRepository: AllStoriesUseCase) : ViewModel() {
    val stories: LiveData<PagingData<AllStoriesModel.stories>> =
        storiesRepository.get_all_stories().cachedIn(viewModelScope)
}