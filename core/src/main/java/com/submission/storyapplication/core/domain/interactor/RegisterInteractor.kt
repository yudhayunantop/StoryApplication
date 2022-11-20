package com.submission.storyapplication.core.domain.interactor

import com.submission.storyapplication.core.domain.models.ResponseModel
import com.submission.storyapplication.core.domain.repoInterface.IRegisterRepository
import com.submission.storyapplication.core.domain.useCase.RegisterUseCase

class RegisterInteractor(val registerRepository: com.submission.storyapplication.core.domain.repoInterface.IRegisterRepository):
    com.submission.storyapplication.core.domain.useCase.RegisterUseCase {
    override suspend fun register(name: String, email: String, password: String): com.submission.storyapplication.core.domain.models.ResponseModel {
        return registerRepository.register(name, email, password)
    }
}