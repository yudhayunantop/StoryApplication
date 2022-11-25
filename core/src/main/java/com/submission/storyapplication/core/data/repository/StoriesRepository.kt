package com.submission.storyapplication.core.data.repository

import com.submission.storyapplication.core.data.remote.ApiEndPoint
import com.submission.storyapplication.core.domain.repoInterface.IAllStoriesRepository
import com.submission.storyapplication.core.data.remote.response.ResponseModel
import com.submission.storyapplication.core.data.database.StoriesDatabase
import com.submission.storyapplication.core.data.database.StoriesPagingSource
import com.submission.storyapplication.core.data.remote.RemoteDataSource
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

class StoriesRepository(
    private val storiesDatabase: StoriesDatabase,
    private val remoteDataSource: RemoteDataSource
) : IAllStoriesRepository {
    override fun getPagingSource() =
        StoriesPagingSource(remoteDataSource)
    override suspend fun addStories(
        token: String,
        description: RequestBody,
        photo: MultipartBody.Part
    )
    : Flow<Resources<ResponseModel>> = remoteDataSource.add_story(
        token,
        description,
        photo
    )
}