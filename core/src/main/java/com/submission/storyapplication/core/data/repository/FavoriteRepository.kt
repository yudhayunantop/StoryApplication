package com.submission.storyapplication.core.data.repository

import com.submission.storyapplication.core.data.local.LocalDataSource
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel
import com.submission.storyapplication.core.domain.repoInterface.IFavoriteRepository
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavoriteRepository(val localDataSource: LocalDataSource) :
    IFavoriteRepository {
    override fun getAllStoriesFavorite(): Flow<Resources<List<AllStoriesModel.stories>>> {
        return flow {
            emit(Resources.Loading(data = null))
            try {
                localDataSource.getAllStoriesFavorite().collect {
                    if (it.isNotEmpty())
                        emit(Resources.Success(data = it))
                    else
                        emit(Resources.Error(message = "No List found"))
                }
            } catch (e: Exception) {
                emit(Resources.Error(message = e.message.toString()))
            }
        }
    }

    override suspend fun insertFavorite(stories: AllStoriesModel.stories): Flow<Resources<String>> {
        return flow {
            emit(Resources.Loading(data = null))
            try {
                localDataSource.insertFavorite(stories)
                emit(Resources.Success(data = "Success"))
            } catch (e: Exception) {
                emit(Resources.Error(message = e.message.toString()))
            }
        }
    }

    override suspend fun deleteFavorite(stories: AllStoriesModel.stories): Flow<Resources<String>> {
        return flow {
            emit(Resources.Loading(data = null))
            try {
                localDataSource.deleteFavorite(stories)
                emit(Resources.Success(data = "Success"))
            } catch (e: Exception) {
                emit(Resources.Error(message = e.message.toString()))
            }
        }
    }

    override fun isRowExist(id : String): Flow<Resources<Boolean>> {
        return flow {
            emit(Resources.Loading(data = null))
            try {
                emit(Resources.Success(data = localDataSource.isRowExist(id)))
            } catch (e: Exception) {
                emit(Resources.Error(message = e.message.toString()))
            }
        }
    }
}