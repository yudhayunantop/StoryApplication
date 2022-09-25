package com.submission.storyapplication.activity

import android.content.Context
import com.submission.storyapplication.api.ApiRetrofit
import com.submission.storyapplication.paging.StoriesRepository
import com.submission.storyapplication.paging.StoriesDatabase

object Injection {

    fun provideRepository(context: Context): StoriesRepository {
        val database = StoriesDatabase.getDatabase(context)
        val apiService = ApiRetrofit().endpoint

        return StoriesRepository(database, apiService)
    }
}