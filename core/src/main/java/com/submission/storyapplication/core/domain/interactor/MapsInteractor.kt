package com.submission.storyapplication.core.domain.interactor

import com.submission.storyapplication.core.domain.models.AllStoriesModel
import com.submission.storyapplication.core.domain.repoInterface.IMapsRepository
import com.submission.storyapplication.core.domain.useCase.MapsUseCase

class MapsInteractor(val mapsRepository: com.submission.storyapplication.core.domain.repoInterface.IMapsRepository):
    com.submission.storyapplication.core.domain.useCase.MapsUseCase {
    override suspend fun get_all_stories(token: String): com.submission.storyapplication.core.domain.models.AllStoriesModel {
        return mapsRepository.get_all_stories_location(token)
    }
}