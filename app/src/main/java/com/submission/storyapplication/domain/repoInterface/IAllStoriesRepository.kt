package com.submission.storyapplication.domain.repoInterface

import com.submission.storyapplication.domain.models.ResponseModel
import com.submission.storyapplication.paging.StoriesPagingSource
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface IAllStoriesRepository {
//    suspend fun get_all_stories(token: String, page: String, size: String): LiveData<PagingData<AllStoriesModel.stories>>
    fun getPagingSource(): StoriesPagingSource
    suspend fun addStories(
            token:String,
            description: RequestBody,
            photo: MultipartBody.Part
    ): ResponseModel
}