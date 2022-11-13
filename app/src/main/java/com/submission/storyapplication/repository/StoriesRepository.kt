package com.submission.storyapplication.repository

import com.submission.storyapplication.api.ApiEndPoint
import com.submission.storyapplication.domain.models.ResponseModel
import com.submission.storyapplication.domain.repoInterface.IAllStoriesRepository
import com.submission.storyapplication.database.StoriesDatabase
import com.submission.storyapplication.database.StoriesPagingSource
import okhttp3.MultipartBody
import okhttp3.RequestBody

class StoriesRepository(
    private val storiesDatabase: StoriesDatabase,
    private val apiEndPoint: ApiEndPoint
) : IAllStoriesRepository {
    override fun getPagingSource() = StoriesPagingSource(apiEndPoint)
    override suspend fun addStories(
        token: String,
        description: RequestBody,
        photo: MultipartBody.Part
    )
            : ResponseModel = apiEndPoint.add_story(
        token,
        description,
        photo
    )

}