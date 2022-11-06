package com.submission.storyapplication.repository

import com.submission.storyapplication.api.ApiEndPoint

class MapsRepository(val apiEndPoint: ApiEndPoint) {
    suspend fun getAllStoriesLocation(token: String)=apiEndPoint.get_all_stories_location(token)
}