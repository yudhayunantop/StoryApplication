package com.submission.storyapplication.viewModel

import androidx.lifecycle.ViewModel
import com.submission.storyapplication.domain.models.AllStoriesModel
import com.submission.storyapplication.domain.useCase.FavoriteUseCase
import com.submission.storyapplication.helper.Resources

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