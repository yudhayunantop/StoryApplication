package com.submission.storyapplication

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.submission.storyapplication.activity.Injection
import com.submission.storyapplication.models.AllStoriesModel
import com.submission.storyapplication.repository.StoriesRepository

class MainViewModel(storiesRepository: StoriesRepository) : ViewModel() {
    val stories: LiveData<PagingData<AllStoriesModel.stories>> =
        storiesRepository.getStories().cachedIn(viewModelScope)
}

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(Injection.provideRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}