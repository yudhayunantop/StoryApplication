package com.submission.storyapplication.ui.viewModel

import androidx.lifecycle.ViewModel
import com.submission.storyapplication.core.domain.useCase.FavoriteUseCase
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel
import com.submission.storyapplication.core.utils.Resources

class DetailViewModel(val favoriteUseCase: FavoriteUseCase) : ViewModel() {
    suspend fun addFavorite(stories: AllStoriesModel.stories): Resources<String> {
        try {
            favoriteUseCase.insertFavorite(stories)
            return Resources.Success(data = "Success")
        } catch (e: Exception) {
            return Resources.Error(message = e.message.toString())
        }
    }
}