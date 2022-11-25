package com.submission.storyapplication.core.domain.useCase

import com.submission.storyapplication.core.data.remote.response.ResponseModel
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface AddStoriesUseCase {
    suspend fun addStories(
        token:String,
        description: RequestBody,
        photo: MultipartBody.Part
    ): ResponseModel
}