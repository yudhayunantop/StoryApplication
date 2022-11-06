package com.submission.storyapplication.domain.repoInterface

import com.submission.storyapplication.domain.models.AllStoriesModel

interface IMapsRepository {
    suspend fun get_all_stories_location(token: String): AllStoriesModel
}