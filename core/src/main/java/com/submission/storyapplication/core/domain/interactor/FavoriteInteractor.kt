package com.submission.storyapplication.core.domain.interactor

import com.submission.storyapplication.core.data.remote.response.AllStoriesModel
import com.submission.storyapplication.core.domain.repoInterface.IFavoriteRepository
import com.submission.storyapplication.core.domain.useCase.FavoriteUseCase
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow

class FavoriteInteractor (val favoriteRepository: IFavoriteRepository):
    FavoriteUseCase {
    override fun getAllStoriesFavorite(): Flow<Resources<List<AllStoriesModel.stories>>> {
        return favoriteRepository.getAllStoriesFavorite()
    }
    override suspend fun insertFavorite(stories: AllStoriesModel.stories):Flow<Resources<String>>{
        return favoriteRepository.insertFavorite(stories)
    }
    override suspend fun  deleteFavorite(stories: AllStoriesModel.stories): Flow<Resources<String>> {
        return favoriteRepository.deleteFavorite(stories)
    }

    override fun isRowExist(id: String): Flow<Resources<Boolean>> {
        return favoriteRepository.isRowExist(id)
    }
}