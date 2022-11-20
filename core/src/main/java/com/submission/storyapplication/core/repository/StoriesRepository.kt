package com.submission.storyapplication.core.repository

import com.submission.storyapplication.core.api.ApiEndPoint
import com.submission.storyapplication.core.domain.models.ResponseModel
import com.submission.storyapplication.core.domain.repoInterface.IAllStoriesRepository
import com.submission.storyapplication.core.database.StoriesDatabase
import com.submission.storyapplication.core.database.StoriesPagingSource
import okhttp3.MultipartBody
import okhttp3.RequestBody

class StoriesRepository(
    private val storiesDatabase: com.submission.storyapplication.core.database.StoriesDatabase,
    private val apiEndPoint: ApiEndPoint
) : com.submission.storyapplication.core.domain.repoInterface.IAllStoriesRepository {
    override fun getPagingSource() =
        com.submission.storyapplication.core.database.StoriesPagingSource(apiEndPoint)
    override suspend fun addStories(
        token: String,
        description: RequestBody,
        photo: MultipartBody.Part
    )
            : com.submission.storyapplication.core.domain.models.ResponseModel = apiEndPoint.add_story(
        token,
        description,
        photo
    )

}