package com.submission.storyapplication.domain.useCase

import com.submission.storyapplication.domain.models.ResponseModel
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface AddStoriesUseCase {
    suspend fun addStories(
        token:String,
        description: RequestBody,
        photo: MultipartBody.Part
    ): ResponseModel
}