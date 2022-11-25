package com.submission.storyapplication.core.data.repository

import com.submission.storyapplication.core.data.remote.ApiEndPoint
import com.submission.storyapplication.core.data.remote.RemoteDataSource
import com.submission.storyapplication.core.domain.repoInterface.IMapsRepository

class MapsRepository(val remoteDataSource: RemoteDataSource):
    IMapsRepository {
    override suspend fun get_all_stories_location(token: String)=remoteDataSource.get_all_stories_location(token)
}