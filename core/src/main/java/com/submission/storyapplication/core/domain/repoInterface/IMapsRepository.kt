package com.submission.storyapplication.core.domain.repoInterface

import com.submission.storyapplication.core.domain.models.AllStoriesModel

interface IMapsRepository {
    suspend fun get_all_stories_location(token: String): com.submission.storyapplication.core.domain.models.AllStoriesModel
}