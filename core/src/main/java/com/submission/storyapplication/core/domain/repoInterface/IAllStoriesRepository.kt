package com.submission.storyapplication.core.domain.repoInterface

import com.submission.storyapplication.core.domain.models.ResponseModel
import com.submission.storyapplication.core.database.StoriesPagingSource
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface IAllStoriesRepository {
//    suspend fun get_all_stories(token: String, page: String, size: String): LiveData<PagingData<AllStoriesModel.stories>>
    fun getPagingSource(): com.submission.storyapplication.core.database.StoriesPagingSource
    suspend fun addStories(
            token:String,
            description: RequestBody,
            photo: MultipartBody.Part
    ): com.submission.storyapplication.core.domain.models.ResponseModel
}