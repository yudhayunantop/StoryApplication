package com.submission.storyapplication.core.domain.interactor

import com.submission.storyapplication.core.domain.models.AllStoriesModel
import com.submission.storyapplication.core.domain.repoInterface.IFavoriteRerpository
import com.submission.storyapplication.core.domain.useCase.FavoriteUseCase
import kotlinx.coroutines.flow.Flow

class FavoriteInteractor (val favoriteRepository: com.submission.storyapplication.core.domain.repoInterface.IFavoriteRerpository):
    com.submission.storyapplication.core.domain.useCase.FavoriteUseCase {
    override fun getAllStoriesFavorite(): List<com.submission.storyapplication.core.domain.models.AllStoriesModel.stories>{
        return favoriteRepository.getAllStoriesFavorite()
    }
    override suspend fun insertFavorite(stories: com.submission.storyapplication.core.domain.models.AllStoriesModel.stories){
        return favoriteRepository.insertFavorite(stories)
    }
    override suspend fun  deleteFavorite(stories: com.submission.storyapplication.core.domain.models.AllStoriesModel.stories) {
        return favoriteRepository.deleteFavorite(stories)
    }
}