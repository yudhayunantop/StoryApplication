package com.submission.storyapplication.domain.interactor

import com.submission.storyapplication.domain.models.ResponseModel
import com.submission.storyapplication.domain.repoInterface.IRegisterRepository
import com.submission.storyapplication.domain.useCase.RegisterUseCase

class RegisterInteractor(val registerRepository: IRegisterRepository): RegisterUseCase {
    override suspend fun register(name: String, email: String, password: String): ResponseModel {
        return registerRepository.register(name, email, password)
    }
}