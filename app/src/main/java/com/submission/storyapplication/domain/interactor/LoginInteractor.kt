package com.submission.storyapplication.domain.interactor

import com.submission.storyapplication.domain.repoInterface.ILoginRepository
import com.submission.storyapplication.domain.models.LoginModel
import com.submission.storyapplication.domain.useCase.LoginUseCase

class LoginInteractor(val loginRepository: ILoginRepository): LoginUseCase {
    override suspend fun login(email: String, password: String): LoginModel {
        return loginRepository.login(email, password)
    }
}