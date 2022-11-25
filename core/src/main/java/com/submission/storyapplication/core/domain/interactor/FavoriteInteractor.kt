package com.submission.storyapplication.core.domain.interactor

import com.submission.storyapplication.core.domain.repoInterface.IFavoriteRerpository
import com.submission.storyapplication.core.domain.useCase.FavoriteUseCase
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel

class FavoriteInteractor (val favoriteRepository: IFavoriteRerpository):
    FavoriteUseCase {
    override fun getAllStoriesFavorite(): List<AllStoriesModel.stories>{
        return favoriteRepository.getAllStoriesFavorite()
    }
    override suspend fun insertFavorite(stories: AllStoriesModel.stories){
        return favoriteRepository.insertFavorite(stories)
    }
    override suspend fun  deleteFavorite(stories: AllStoriesModel.stories) {
        return favoriteRepository.deleteFavorite(stories)
    }
}