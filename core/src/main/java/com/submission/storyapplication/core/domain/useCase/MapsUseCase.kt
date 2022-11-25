package com.submission.storyapplication.core.domain.useCase

import com.submission.storyapplication.core.data.remote.response.AllStoriesModel

interface MapsUseCase {
    suspend fun get_all_stories(token: String): AllStoriesModel
}