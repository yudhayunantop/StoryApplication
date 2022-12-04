package com.submission.storyapplication.core.domain.interactor

import com.submission.storyapplication.core.data.remote.network.ApiResponse
import com.submission.storyapplication.core.domain.repoInterface.IAllStoriesRepository
import com.submission.storyapplication.core.domain.useCase.AddStoriesUseCase
import com.submission.storyapplication.core.data.remote.response.ResponseModel
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AddStoriesInteractor (val repository: IAllStoriesRepository):
    AddStoriesUseCase {
    override suspend fun addStories(
        token: String,
        description: RequestBody,
        photo: MultipartBody.Part
    ): Flow<Resources<ResponseModel>> {
        return repository.addStories(token,description, photo)
    }
}