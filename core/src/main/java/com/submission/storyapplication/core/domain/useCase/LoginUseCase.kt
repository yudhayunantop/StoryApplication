package com.submission.storyapplication.core.domain.useCase

import com.submission.storyapplication.core.data.remote.response.LoginModel
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    suspend fun login(email: String, password: String): Flow<Resources<LoginModel.login>>
}