package com.submission.storyapplication.core.domain.interactor

import com.submission.storyapplication.core.domain.repoInterface.ILoginRepository
import com.submission.storyapplication.core.domain.models.LoginModel
import com.submission.storyapplication.core.domain.useCase.LoginUseCase

class LoginInteractor(val loginRepository: com.submission.storyapplication.core.domain.repoInterface.ILoginRepository):
    com.submission.storyapplication.core.domain.useCase.LoginUseCase {
    override suspend fun login(email: String, password: String): com.submission.storyapplication.core.domain.models.LoginModel {
        return loginRepository.login(email, password)
    }
}