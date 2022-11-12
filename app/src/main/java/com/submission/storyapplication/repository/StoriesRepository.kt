package com.submission.storyapplication.repository

import com.submission.storyapplication.api.ApiEndPoint
import com.submission.storyapplication.domain.repoInterface.IAllStoriesRepository
import com.submission.storyapplication.paging.StoriesDatabase
import com.submission.storyapplication.paging.StoriesPagingSource

class StoriesRepository(private val storiesDatabase: StoriesDatabase, private val apiEndPoint: ApiEndPoint): IAllStoriesRepository {
//    fun getStories(): LiveData<PagingData<AllStoriesModel.stories>> {
//        return Pager(
//            config = PagingConfig(
//                pageSize = 5
//            ),
//            pagingSourceFactory = {
//                StoriesPagingSource(apiEndPoint)
//            }
//        ).liveData
//    }

    override fun getPagingSource()=StoriesPagingSource(apiEndPoint)
}