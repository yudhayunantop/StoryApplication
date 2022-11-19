package com.submission.storyapplication.viewModel

import androidx.lifecycle.ViewModel
import com.submission.storyapplication.domain.models.AllStoriesModel
import com.submission.storyapplication.domain.useCase.FavoriteUseCase
import com.submission.storyapplication.helper.Resources
import kotlinx.coroutines.flow.flow

class FavoriteViewModel(private val favoriteUseCase: FavoriteUseCase) : ViewModel() {
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

    suspend fun delete(stories: AllStoriesModel.stories) {
        favoriteUseCase.deleteFavorite(stories)
    }

}