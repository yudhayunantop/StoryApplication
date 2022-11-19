package com.submission.storyapplication.viewModel

import androidx.lifecycle.ViewModel
import com.submission.storyapplication.domain.models.AllStoriesModel
import com.submission.storyapplication.domain.useCase.FavoriteUseCase
import com.submission.storyapplication.helper.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FavoriteViewModel(val favoriteUseCase: FavoriteUseCase) : ViewModel() {
    fun getAllFavorite() =  flow {
        emit(Resources.Loading(data = null))
        try {
            favoriteUseCase.getAllStoriesFavorite().flowOn(Dispatchers.IO).collect { listStories ->
                emit(Resources.Success(data = listStories))
            }
        } catch (e: Exception) {
            emit(Resources.Error(message = e.message.toString()))
        }
    }
}