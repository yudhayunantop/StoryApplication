package com.submission.storyapplication.domain.interactor

import com.submission.storyapplication.domain.models.AllStoriesModel
import com.submission.storyapplication.domain.repoInterface.IFavoriteRerpository
import com.submission.storyapplication.domain.useCase.FavoriteUseCase
import kotlinx.coroutines.flow.Flow

class FavoriteInteractor (val favoriteRepository: IFavoriteRerpository): FavoriteUseCase{
    override fun getAllStoriesFavorite(): Flow<List<AllStoriesModel.stories>>{
        return favoriteRepository.getAllStoriesFavorite()
    }
    override suspend fun insertFavorite(stories: AllStoriesModel.stories){
        return favoriteRepository.insertFavorite(stories)
    }
    override suspend fun  deleteFavorite(stories: AllStoriesModel.stories) {
        return favoriteRepository.deleteFavorite(stories)
    }
}