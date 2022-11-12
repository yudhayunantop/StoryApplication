package com.submission.storyapplication.domain.repoInterface

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.submission.storyapplication.domain.models.AllStoriesModel
import com.submission.storyapplication.paging.StoriesPagingSource

interface IAllStoriesRepository {
//    suspend fun get_all_stories(token: String, page: String, size: String): LiveData<PagingData<AllStoriesModel.stories>>
    fun getPagingSource(): StoriesPagingSource
}