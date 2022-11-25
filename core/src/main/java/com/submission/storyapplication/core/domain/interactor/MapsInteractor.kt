package com.submission.storyapplication.core.domain.interactor

import com.submission.storyapplication.core.domain.repoInterface.IMapsRepository
import com.submission.storyapplication.core.domain.useCase.MapsUseCase
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel

class MapsInteractor(val mapsRepository: IMapsRepository):
    MapsUseCase {
    override suspend fun get_all_stories(token: String): AllStoriesModel {
        return mapsRepository.get_all_stories_location(token)
    }
}