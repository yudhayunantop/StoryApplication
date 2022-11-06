package com.submission.storyapplication.domain.useCase

import com.submission.storyapplication.domain.models.AllStoriesModel

interface MapsUseCase {
    suspend fun get_all_stories(token: String): AllStoriesModel
}