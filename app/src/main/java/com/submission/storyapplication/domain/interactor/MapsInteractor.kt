package com.submission.storyapplication.domain.interactor

import com.submission.storyapplication.domain.models.AllStoriesModel
import com.submission.storyapplication.domain.repoInterface.IMapsRepository
import com.submission.storyapplication.domain.useCase.MapsUseCase

class MapsInteractor(val mapsRepository: IMapsRepository): MapsUseCase {
    override suspend fun get_all_stories(token: String): AllStoriesModel {
        return mapsRepository.get_all_stories_location(token)
    }
}