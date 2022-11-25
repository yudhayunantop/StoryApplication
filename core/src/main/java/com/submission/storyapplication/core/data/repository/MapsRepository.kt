package com.submission.storyapplication.core.data.repository

import com.submission.storyapplication.core.data.remote.ApiEndPoint
import com.submission.storyapplication.core.domain.repoInterface.IMapsRepository

class MapsRepository(val apiEndPoint: ApiEndPoint):
    IMapsRepository {
    override suspend fun get_all_stories_location(token: String)=apiEndPoint.get_all_stories_location(token)
}