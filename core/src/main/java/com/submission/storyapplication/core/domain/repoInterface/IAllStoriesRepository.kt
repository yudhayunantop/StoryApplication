package com.submission.storyapplication.core.domain.repoInterface

import com.submission.storyapplication.core.data.remote.response.ResponseModel
import com.submission.storyapplication.core.data.database.StoriesPagingSource
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface IAllStoriesRepository {
//    suspend fun get_all_stories(token: String, page: String, size: String): LiveData<PagingData<AllStoriesModel.stories>>
    fun getPagingSource(): StoriesPagingSource
    suspend fun addStories(
            token:String,
            description: RequestBody,
            photo: MultipartBody.Part
    ): Flow<Resources<ResponseModel>>
}