package com.submission.storyapplication.ui.viewModel

import androidx.lifecycle.ViewModel
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel
import com.submission.storyapplication.core.domain.useCase.FavoriteUseCase

class DetailViewModel(val favoriteUseCase: FavoriteUseCase) : ViewModel() {
    suspend fun addFavorite(stories: AllStoriesModel.stories)= favoriteUseCase.insertFavorite(stories)
}