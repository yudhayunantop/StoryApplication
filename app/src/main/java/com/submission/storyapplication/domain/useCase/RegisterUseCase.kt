package com.submission.storyapplication.domain.useCase

import com.submission.storyapplication.domain.models.LoginModel
import com.submission.storyapplication.domain.models.ResponseModel

interface RegisterUseCase {
    suspend fun register(name: String, email: String, password: String): ResponseModel
}