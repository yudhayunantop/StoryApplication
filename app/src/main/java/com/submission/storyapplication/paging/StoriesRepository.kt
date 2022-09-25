package com.submission.storyapplication.paging

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.submission.storyapplication.api.ApiEndPoint
import com.submission.storyapplication.models.AllStoriesModel

class StoriesRepository(private val storiesDatabase: StoriesDatabase, private val apiEndPoint: ApiEndPoint) {
    fun getStories(): LiveData<PagingData<AllStoriesModel.stories>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                StoriesPagingSource(apiEndPoint)
            }
        ).liveData
    }
}