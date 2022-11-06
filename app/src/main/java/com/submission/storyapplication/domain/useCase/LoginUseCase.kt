package com.submission.storyapplication.domain.useCase

import com.submission.storyapplication.domain.models.LoginModel

interface LoginUseCase {
    suspend fun login(email: String, password: String): LoginModel
}