package com.submission.storyapplication.viewModel

import androidx.lifecycle.ViewModel
import com.submission.storyapplication.core.domain.models.AllStoriesModel
import com.submission.storyapplication.core.domain.useCase.FavoriteUseCase
import com.submission.storyapplication.core.helper.Resources

class DetailViewModel(val favoriteUseCase: com.submission.storyapplication.core.domain.useCase.FavoriteUseCase) : ViewModel() {
    suspend fun addFavorite(stories: com.submission.storyapplication.core.domain.models.AllStoriesModel.stories): Resources<String> {
        try {
            favoriteUseCase.insertFavorite(stories)
            return Resources.Success(data = "Success")
        } catch (e: Exception) {
            return Resources.Error(message = e.message.toString())
        }
    }
}