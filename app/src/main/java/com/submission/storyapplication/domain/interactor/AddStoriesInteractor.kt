package com.submission.storyapplication.domain.interactor

import com.submission.storyapplication.domain.models.ResponseModel
import com.submission.storyapplication.domain.repoInterface.IAllStoriesRepository
import com.submission.storyapplication.domain.useCase.AddStoriesUseCase
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AddStoriesInteractor (val repository: IAllStoriesRepository):AddStoriesUseCase{
    override suspend fun addStories(
        token: String,
        description: RequestBody,
        photo: MultipartBody.Part
    ): ResponseModel {
        return repository.addStories(token,description, photo)
    }
}