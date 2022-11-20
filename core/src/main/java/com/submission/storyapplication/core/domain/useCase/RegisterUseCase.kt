package com.submission.storyapplication.core.domain.useCase

import com.submission.storyapplication.core.domain.models.ResponseModel

interface RegisterUseCase {
    suspend fun register(name: String, email: String, password: String): com.submission.storyapplication.core.domain.models.ResponseModel
}