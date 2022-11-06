package com.submission.storyapplication.repository

import com.submission.storyapplication.api.ApiEndPoint
import com.submission.storyapplication.domain.repoInterface.IMapsRepository

class MapsRepository(val apiEndPoint: ApiEndPoint): IMapsRepository {
    override suspend fun get_all_stories_location(token: String)=apiEndPoint.get_all_stories_location(token)
}