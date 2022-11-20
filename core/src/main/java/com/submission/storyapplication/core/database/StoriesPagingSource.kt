package com.submission.storyapplication.core.database

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.submission.storyapplication.core.api.ApiEndPoint
import com.submission.storyapplication.core.preferences.Preferences

class StoriesPagingSource(private val apiEndPoint: ApiEndPoint) : PagingSource<Int, com.submission.storyapplication.core.domain.models.AllStoriesModel.stories>() {
    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, com.submission.storyapplication.core.domain.models.AllStoriesModel.stories> {
        return try {
            val position = params.key ?: com.submission.storyapplication.core.database.StoriesPagingSource.Companion.INITIAL_PAGE_INDEX
            val token =  "Bearer ${Preferences.getToken()}"
            val responseData = apiEndPoint.get_all_stories(
                token,
                position,
                params.loadSize)

            LoadResult.Page(
                data = responseData.listStory ?: emptyList(),
                prevKey = if (position == com.submission.storyapplication.core.database.StoriesPagingSource.Companion.INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.listStory.isNullOrEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, com.submission.storyapplication.core.domain.models.AllStoriesModel.stories>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
