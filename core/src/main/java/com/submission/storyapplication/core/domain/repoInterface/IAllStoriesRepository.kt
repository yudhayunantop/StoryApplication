package com.submission.storyapplication.core.domain.repoInterface

import com.submission.storyapplication.core.data.local.StoriesPagingSource
import com.submission.storyapplication.core.data.remote.network.ApiResponse
import com.submission.storyapplication.core.data.remote.response.ResponseModel
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface IAllStoriesRepository {
    fun getPagingSource(): StoriesPagingSource
    suspend fun addStories(
            token:String,
            description: RequestBody,
            photo: MultipartBody.Part
    ): Flow<ApiResponse<ResponseModel>>
}