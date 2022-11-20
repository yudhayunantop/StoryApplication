package com.submission.storyapplication.core.domain.useCase

import com.submission.storyapplication.core.domain.models.LoginModel

interface LoginUseCase {
    suspend fun login(email: String, password: String): com.submission.storyapplication.core.domain.models.LoginModel
}