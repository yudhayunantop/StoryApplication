package com.submission.storyapplication.utils

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.submission.storyapplication.models.AllStoriesModel

object DataDummy {
    fun generateDummyStoriesEntity(): LiveData<PagingData<AllStoriesModel.stories>> {
        val storiesList = LiveData<PagingData<AllStoriesModel.stories>>()
        for (i in 0..10) {
            val stories = AllStoriesModel.stories(
                "$i",
                "Budi",
                "Lorem ipsum",
                "https://www.dicoding.com/",
                "2022-02-02",
                37.4220936F,
                -122.08392F
            )
            storiesList.add(stories)
        }
        return storiesList
    }
}