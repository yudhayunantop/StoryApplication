package com.submission.storyapplication.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.recyclerview.widget.ListUpdateCallback
import com.submission.storyapplication.LiveDataTestUtils
import com.submission.storyapplication.LiveDataTestUtils.Companion.getOrAwaitValue
import com.submission.storyapplication.MainDispatcherRule
import com.submission.storyapplication.MainViewModel
import com.submission.storyapplication.adapter.StoriesAdapter
import com.submission.storyapplication.core.domain.models.AllStoriesModel
import com.submission.storyapplication.core.repository.StoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    @Mock
    private lateinit var storiesRepository: com.submission.storyapplication.core.repository.StoriesRepository

    @Test
    fun `when Get Quote Should Not Null and Return Success`() = runTest {
        val dummyQuote = LiveDataTestUtils.DataDummy.generateDummyStoriesResponse()
        val data: PagingData<com.submission.storyapplication.core.domain.models.AllStoriesModel.stories> = StoryPagingSource.snapshot(dummyQuote)
        val expectedQuote = MutableLiveData<PagingData<com.submission.storyapplication.core.domain.models.AllStoriesModel.stories>>()
        expectedQuote.value = data
        Mockito.`when`(storiesRepository.getStories()).thenReturn(expectedQuote)

        val mainViewModel = MainViewModel(storiesRepository)
        val actualQuote: PagingData<com.submission.storyapplication.core.domain.models.AllStoriesModel.stories> = mainViewModel.stories.getOrAwaitValue()

        val differ = AsyncPagingDataDiffer(
            diffCallback = StoriesAdapter.DIFF_CALLBACK,
            updateCallback = noopListUpdateCallback,
            workerDispatcher = Dispatchers.Main,
        )
        differ.submitData(actualQuote)

        Assert.assertNotNull(differ.snapshot())
        Assert.assertEquals(dummyQuote, differ.snapshot())
        Assert.assertEquals(dummyQuote.size, differ.snapshot().size)
        Assert.assertEquals(dummyQuote[0].id, differ.snapshot()[0]?.id)
    }
}

class StoryPagingSource : PagingSource<Int, LiveData<List<com.submission.storyapplication.core.domain.models.AllStoriesModel.stories>>>() {
    companion object {
        fun snapshot(items: List<com.submission.storyapplication.core.domain.models.AllStoriesModel.stories>): PagingData<com.submission.storyapplication.core.domain.models.AllStoriesModel.stories> {
            return PagingData.from(items)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LiveData<List<com.submission.storyapplication.core.domain.models.AllStoriesModel.stories>>>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LiveData<List<com.submission.storyapplication.core.domain.models.AllStoriesModel.stories>>> {
        return LoadResult.Page(emptyList(), 0, 1)
    }
}

val noopListUpdateCallback = object : ListUpdateCallback {
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
}