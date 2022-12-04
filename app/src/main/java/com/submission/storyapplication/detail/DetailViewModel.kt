package com.submission.storyapplication.detail

import androidx.lifecycle.ViewModel
import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel
import com.submission.storyapplication.core.domain.useCase.FavoriteUseCase

class DetailViewModel(val favoriteUseCase: FavoriteUseCase) : ViewModel() {
    suspend fun addFavorite(stories: StoriesEntity)= favoriteUseCase.insertFavorite(stories)
    fun isRowExist(id:String) = favoriteUseCase.isRowExist(id = id)
    suspend fun delete(stories: StoriesEntity)= favoriteUseCase.deleteFavorite(stories)
}