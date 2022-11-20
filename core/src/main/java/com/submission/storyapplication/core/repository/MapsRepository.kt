package com.submission.storyapplication.core.repository

import com.submission.storyapplication.core.api.ApiEndPoint
import com.submission.storyapplication.core.domain.repoInterface.IMapsRepository

class MapsRepository(val apiEndPoint: ApiEndPoint):
    com.submission.storyapplication.core.domain.repoInterface.IMapsRepository {
    override suspend fun get_all_stories_location(token: String)=apiEndPoint.get_all_stories_location(token)
}