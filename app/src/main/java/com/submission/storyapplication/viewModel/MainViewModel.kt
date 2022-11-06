package com.submission.storyapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.submission.storyapplication.models.AllStoriesModel
import com.submission.storyapplication.repository.StoriesRepository

class MainViewModel(storiesRepository: StoriesRepository) : ViewModel() {
    val stories: LiveData<PagingData<AllStoriesModel.stories>> =
        storiesRepository.getStories().cachedIn(viewModelScope)
}