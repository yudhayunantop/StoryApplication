package com.submission.storyapplication.core.domain.useCase

import com.submission.storyapplication.core.domain.models.AllStoriesModel

interface MapsUseCase {
    suspend fun get_all_stories(token: String): com.submission.storyapplication.core.domain.models.AllStoriesModel
}