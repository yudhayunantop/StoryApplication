package com.submission.storyapplication.favorit.ui.viewModel

import androidx.lifecycle.ViewModel
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel
import com.submission.storyapplication.core.domain.useCase.FavoriteUseCase

class FavoriteViewModel(private val favoriteUseCase: FavoriteUseCase) : ViewModel() {
    fun getAllFavorite() = favoriteUseCase.getAllStoriesFavorite()

    suspend fun delete(stories: AllStoriesModel.stories)= favoriteUseCase.deleteFavorite(stories)
}