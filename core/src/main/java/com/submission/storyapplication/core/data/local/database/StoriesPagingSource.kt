package com.submission.storyapplication.core.data.local.database

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.submission.storyapplication.core.data.remote.RemoteDataSource
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel
import com.submission.storyapplication.core.utils.Resources
import com.submission.storyapplication.core.utils.preferences.Preferences

class StoriesPagingSource(private val remoteDataSource: RemoteDataSource) :
    PagingSource<Int, AllStoriesModel.stories>() {
    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AllStoriesModel.stories> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val token = "Bearer ${Preferences.getToken()}"
//            val responseData = remoteDataSource.get_all_stories(
//                token,
//                position,
//                params.loadSize)
            var listStory = listOf<AllStoriesModel.stories>()
            remoteDataSource.getAllStories(
                token,
                position,
                params.loadSize
            ).collect { resource ->
                when (resource) {
                    is Resources.Success -> {
                        listStory = resource.data as List<AllStoriesModel.stories>
                    }
                    is Resources.Error -> {}
                    is Resources.Loading -> {}
                }

            }

            LoadResult.Page(
                data = listStory,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (listStory.isNullOrEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AllStoriesModel.stories>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
