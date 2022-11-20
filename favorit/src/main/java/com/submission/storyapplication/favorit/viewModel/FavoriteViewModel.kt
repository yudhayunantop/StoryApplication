package com.submission.storyapplication.favorit.viewModel

import androidx.lifecycle.ViewModel
import com.submission.storyapplication.core.domain.models.AllStoriesModel
import com.submission.storyapplication.core.domain.useCase.FavoriteUseCase
import com.submission.storyapplication.core.helper.Resources
import kotlinx.coroutines.flow.flow

class FavoriteViewModel(private val favoriteUseCase: com.submission.storyapplication.core.domain.useCase.FavoriteUseCase) : ViewModel() {
    fun getAllFavorite() = flow {
        emit(Resources.Loading(data = null))
        try {
            val listFavorite = favoriteUseCase.getAllStoriesFavorite()
            if (listFavorite.isNotEmpty())
                emit(Resources.Success(data = listFavorite))
            else
                emit(Resources.Error(message = "No List found"))
        } catch (e: Exception) {
            emit(Resources.Error(message = e.message.toString()))
        }
    }

    suspend fun delete(stories: com.submission.storyapplication.core.domain.models.AllStoriesModel.stories) {
        favoriteUseCase.deleteFavorite(stories)
    }

}