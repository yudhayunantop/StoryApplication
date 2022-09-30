package com.submission.storyapplication.database

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.submission.storyapplication.MainViewModel
import com.submission.storyapplication.models.AllStoriesModel
import com.submission.storyapplication.paging.StoriesRepository
import com.submission.storyapplication.utils.DataDummy
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest{
    @Mock
    private lateinit var storiesRepository : StoriesRepository
    private lateinit var mainViewModel: MainViewModel
    private val dummyStories = DataDummy.generateDummyStoriesEntity()

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(storiesRepository)
    }

    @Test
    fun `when Get Stories Should Not Null and Return Success`() {
        val expectedStories = LiveData<PagingData<AllStoriesModel.stories>>()
        expectedStories.value = PagingData.(dummyStories)
        `when`(storiesRepository.getStories()).thenReturn(expectedStories)
        val actualNews = mainViewModel.stories
        Assert.assertNotNull(actualNews)
    }
}