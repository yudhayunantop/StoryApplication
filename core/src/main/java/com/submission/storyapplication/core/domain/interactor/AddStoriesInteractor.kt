package com.submission.storyapplication.core.domain.interactor

import com.submission.storyapplication.core.domain.models.ResponseModel
import com.submission.storyapplication.core.domain.repoInterface.IAllStoriesRepository
import com.submission.storyapplication.core.domain.useCase.AddStoriesUseCase
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AddStoriesInteractor (val repository: com.submission.storyapplication.core.domain.repoInterface.IAllStoriesRepository):
    com.submission.storyapplication.core.domain.useCase.AddStoriesUseCase {
    override suspend fun addStories(
        token: String,
        description: RequestBody,
        photo: MultipartBody.Part
    ): com.submission.storyapplication.core.domain.models.ResponseModel {
        return repository.addStories(token,description, photo)
    }
}